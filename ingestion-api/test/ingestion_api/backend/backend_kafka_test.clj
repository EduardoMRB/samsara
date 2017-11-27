(ns ingestion-api.backend.backend-kafka-test
  (:require [ingestion-api.backend.backend-kafka :as backend-kafka]
            [midje.sweet :refer :all]))

(facts "About check-config"
  (fact "Topics can be a single string or a map from namespace to topic name"
    (backend-kafka/check-config {"topic" "a topic"
                                 "metadata.broker.list" ""}) => {"topic" "a topic"
                                                                 "metadata.broker.list" ""}

    (backend-kafka/check-config {"topic" {"messages" "ingestion"
                                          "events"   "ingestion-events"
                                          "default"  "ingestion"}
                                 "metadata.broker.list" ""}) => {"topic" {"messages" "ingestion"
                                                                          "events"   "ingestion-events"
                                                                          "default"  "ingestion"}
                                                                 "metadata.broker.list" ""}))


(facts "About determine-topic"
  (backend-kafka/determine-topic "ingestion" ..event..) => "ingestion"

  (backend-kafka/determine-topic
   {"messages" "ingestion"
    "events"   "events"
    "default"  "default"}
   {:eventName "namespaceless.event.name"}) => "default"

  (backend-kafka/determine-topic
   {"messages" "ingestion"
    "events"   "events"
    "default"  "default"}
   {:eventName "messages/message.sent"}) => "ingestion"

  (backend-kafka/determine-topic
   {"messages" "ingestion"
    "events"   "events"
    "default"  "default"}
   {:eventName "events/item.added"}) => "events")
