variable "access_key" {}
variable "secret_key" {}
variable "region" {
    default = "eu-west-1"
}
variable "zone1" {
    default = "eu-west-1a"
}
variable "zone2" {
    default = "eu-west-1b"
}
variable "zone3" {
    default = "eu-west-1c"
}

variable "key_name" {
    default = ""
}
variable "zookeeper_type" {
    default = "t2.micro"
}
variable "kafka_type" {
    default = "t2.micro"
}
