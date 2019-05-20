(ns cn.lhfei.routes.profile
  (:require [compojure.core :refer :all]
            [noir.response :as resp]
            [noir.session :as session]
            [cn.lhfei.models.person :as db]
            [cn.lhfei.views.layout :as layout]
            [cn.lhfei.views.profile :as view]))

(defn remote-user [id]
  (db/remove-person id)
  (session/clear!)
  (resp/redirect "/"))

(defn profile-page []
  (layout/common
    (view/profile-page)))

(defn delete-profile []
  (remote-user (session/get :user-id)))

(defroutes profile-routes
           (GET "/profile" [] (profile-page))
           (POST "/profile/delete" [] (delete-profile)))
