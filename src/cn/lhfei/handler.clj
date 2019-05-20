(ns cn.lhfei.handler
  ;;(:use compojure.core)
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [noir.util.middleware :as noir-middleware]
            [noir.session :as session]
            [migratus.core :as migratus]
            [ring.adapter.jetty :as jetty] ; add this require
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.anti-forgery :refer [wrap-anti-forgery]]
            [cn.lhfei.template :refer [tpl-helloworld]]
    ;[cn.lhfei.views.views :as views]
            [cn.lhfei.routes.home :refer [home-routes]]
            [cn.lhfei.routes.profile :refer [profile-routes]]
            [cn.lhfei.routes.auth :refer [auth-routes]]
            [cn.lhfei.views.layout :as layout])
  (:gen-class))

;(defroutes app-routes
;           ;;(GET "/" [] (tpl-helloworld "Hello Hefei"))
;           (GET "/" [] (views/home-page))
;
;           (GET "/add-location"
;                []
;                (views/add-location-page))
;
;           (POST "/add-location"
;                 {params :params}
;                 (views/add-location-results-page params))
;
;           (GET "/locaton/:loc-id"
;                [loc-id]
;                (views/location-page loc-id))
;           (GET "/all-locations"
;                []
;                (views/all-locations-page))
;
;           (route/resources "/")
;           (route/not-found "Not Found"))

;(def app
;  (noir-middleware/app-handler
;    [app-routes]
;    :middleware [wrap-anti-forgery]
;    ))

(def migratus-config
  {:store :database
   :migration-dir "migrations"
   :db {:classname "com.mysql.jdbc.Driver"
        :subprotocol "mysql"
        :subname "//192.168.149.91/clj-tutorial"
        :user "root"
        :password "Lhfei"}})

(defn init []
  (do
    (migratus/migrate migratus-config)))

(defn user-page [_]
  (session/get :user-id))

(defn not-found []
  (layout/base
    [:center
     [:h1 "404. Page not found!"]]))

(defroutes app-routes
           (route/resources "/")
           (route/not-found (not-found)))

(def app
  (noir-middleware/app-handler
    [auth-routes
     home-routes
     profile-routes
     app-routes]
    :middleware [wrap-anti-forgery]
    :access-rules [user-page]))

(defn start [port]
  (jetty/run-jetty #'app {:port port
                         :join? false}))
(defn -main
  [& [port]]
  (let [port (Integer. (or port
                           (System/getenv "PORT")
                           9001))]
    ;;(jetty/run-jetty #'app {:port  port :join? false})
  (start port))

  )
