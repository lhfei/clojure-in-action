(ns cn.lhfei.template
  (:require [net.cgrand.enlive-html :refer [deftemplate content]]))

(deftemplate tpl-helloworld "public/helloworld.html"
             [value]
             [:#message] (content value))