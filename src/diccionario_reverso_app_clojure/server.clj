(ns diccionario-reverso-app-clojure.server
  (:require [io.pedestal.http :as http]
            [diccionario-reverso-app-clojure.diplomat.http-server :refer [routes]]))

(def server 
  (http/create-server {::http/routes routes
                       ::http/type :jetty
                       ::http/port 8890}))

(defn -main []
  (http/start server))