(ns diccionario-reverso-app-clojure.diplomat.http-server
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route :refer [expand-routes]]
            [diccionario-reverso-app-clojure.diplomat.http_in.search :as http-in.search]))


(def search-routes
  #{["/search"
     :post
     [(body-params/body-params) http-in.search/search http/json-body]
     :route-name
     :search]}
  )

(def routes
  (expand-routes
    search-routes))