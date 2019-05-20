(ns cn.lhfei.db
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec {:classname "com.mysql.jdbc.Driver"
              :subprotocol "mysql"
              :subname "//192.168.149.91/clj-tutorial"
              :user "root"
              :password "Lhfei"})
