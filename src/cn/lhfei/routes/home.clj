(ns cn.lhfei.routes.home
  (:require [compojure.core :refer :all]
            [cn.lhfei.views.home :as view]
            [cn.lhfei.views.layout :as layout]))

(defn home []
  (layout/common (view/home)))

(defroutes home-routes
           (GET "/" [] (home)))
