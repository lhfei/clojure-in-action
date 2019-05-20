(ns cn.lhfei.views.profile
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.element :refer :all]
            [clj-time.coerce :as c]
            [clj-time.format :as f]
            [cn.lhfei.models.person :as db]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [noir.session :as session]))

(defn profile-page []
  (let [user (db/get-person-by-id (session/get :user-id))]
    [:div
     [:h1 "Profile"]
     [:div {:class "user-info"}
      [:p
       [:span "Name: "]
       [:strong (:name user)]]
      [:p
       [:span "Email: "]
       [:strong (:email user)]]
      [:p
       [:span "Member since: "]
       [:strong (f/unparse (f/formatters :date) (c/from-date (:timestamp user)))]]]
     [:hr]
     [:form {:action "/profile/delete" :method "POST"}
      (anti-forgery-field)
      [:button {:class "btn btn-danger btn-sm"} "Delete account"]]]))
