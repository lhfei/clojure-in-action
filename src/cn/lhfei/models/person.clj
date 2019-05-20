(ns cn.lhfei.models.person
  (:require [clojure.java.jdbc :as sql]
            [cn.lhfei.db :as db]))

(defn add-person [person]
  (sql/insert! db/db-spec :persons person))

(defn get-person-by-email [email]
  (sql/query db/db-spec
             ["SELECT * FROM persons WHERE email = ?", email]
             {:result-set-fn first}))
(defn get-person-by-id [id]
  (sql/query db/db-spec
             ["SELECT * FROM persons WHERE id = ?", id]
             {:result-set-fn first}))
(defn remove-person [id]
  (sql/delete! db/db-spec :persons ["id=?", id]))

(defn remove-person-by-email [email]
  (sql/delete! db/db-spec :persons ["email=?", email]))