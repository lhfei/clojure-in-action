(defproject clojure-in-action "0.1.0-SNAPSHOT"
  :description "Clojure Programming in action."
  :min-lein-version "2.0.0"
  :url "https://github.com/lhfei/clojure-in-action.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [compojure/compojure "1.6.1"]
                 [hiccup/hiccup "1.0.5"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [lib-noir/lib-noir "0.9.9"]
                 [enlive/enlive "1.1.6"]
                 [prone/prone "1.6.1"]
                 ;; JDBC
                 [org.clojure/java.jdbc "0.7.8"]
                 [mysql/mysql-connector-java "5.1.45"]
                 ;; Migratues
                 [migratus/migratus "1.1.7"]
                 ;; Ring
                 [ring/ring-anti-forgery "1.3.0"]
                 [ring/ring-defaults "0.3.2"]]
  :main cn.lhfei.handler
  :plugins [[lein-ring "0.12.4"]
            [lein-ancient "0.6.15"]]

  :ring {:handler cn.lhfei.handler/app
         ;;:init cn.lhfei.handler/init
         }
  :profiles
  {:dev {:dependencies [[javax.servlet/javax.servlet-api "4.0.1"]
                        [kerodon "0.9.0"]
                        [ring/ring-mock "0.3.2"]]
         :ring         {:stacktrace-middleware prone.middleware/wrap-exceptions}}
   ;;:uberjar {:aot :all}
   }
  )
