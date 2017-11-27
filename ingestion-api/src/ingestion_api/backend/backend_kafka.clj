(ns ingestion-api.backend.backend-kafka
  (:refer-clojure :exclude [send])
  (:require [clj-kafka.producer :as kp]
            [ingestion-api.backend.backend-protocol :refer [send]]
            [samsara.utils :refer [to-json]]
            [schema.core :as s])
  (:import ingestion_api.backend.backend_protocol.EventsQueueingBackend))

(def default-producer-config
  "Default configuration for the Kafka producer.
  By default the events will be sent asynchronously and
  the producer will wait for all brokers to acknowledge
  the events."

  {"serializer.class"         "kafka.serializer.StringEncoder"
   "partitioner.class"        "kafka.producer.DefaultPartitioner"
   "request.required.acks"    "-1"
   "producer.type"            "async"
   "message.send.max.retries" "5" })


(defn topic-by-namespace [topics event]
  (let [event-name (keyword (:eventName event))]
    (if-let [event-ns (namespace event-name)]
      (get topics event-ns)
      (get topics "default"))))


(defn determine-topic [topic-or-topics events]
  (if (string? topic-or-topics)
    topic-or-topics
    (topic-by-namespace topic-or-topics events)))


;;
;;  Kafka backend sends the events into a Kafka topic as single
;;  json lines.
;;
(deftype KafkaBackend [conf topic-or-topics producer]
  EventsQueueingBackend

  (send [_ events]
    (->> events
         (map (juxt (partial determine-topic topic-or-topics)
                    :sourceId
                    to-json))
         (map (fn [[topic key message]] (kp/message topic key message)))
         (kp/send-messages producer))))


(def Topic
  (s/either
   s/Str
   {(s/required-key "default") s/Str
    s/Str s/Str}))


(defn check-config
  "Check the validity of a Kafka configuration"
  [config]
  (s/validate
   {(s/required-key "topic") Topic
    (s/required-key "metadata.broker.list") s/Str
    s/Str s/Str}
   config))


(defn make-kafka-backend
  "Create a kafka backend"
  [config]
  (let [{:strs [topic] :as cfg} (->> config
                                     (map (fn [[k v]] [(name k) (if (map? v) v (str v))]))
                                     (into {})
                                     (merge default-producer-config))

        ;; check config
        _ (check-config cfg)

        ;; connect to brokers
        producer (kp/producer (dissoc cfg "topic"))]

    (KafkaBackend. cfg topic producer)))
