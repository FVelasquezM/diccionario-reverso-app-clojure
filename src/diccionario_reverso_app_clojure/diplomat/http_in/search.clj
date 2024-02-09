(ns diccionario-reverso-app-clojure.diplomat.http_in.search
  (:require [diccionario-reverso-app-clojure.controllers.search :as controllers.search]
            [diccionario-reverso-app-clojure.adapters.loan :as adapters.loan]))

(defn response
  [status body & {:as headers}]
  {:status status :body body :headers headers})

(def response-ok (partial response 200))
(def response-bad-request (partial response 400))

(defn search
  "Searches the 10 closest words to a phrase."
  ([{:keys [headers params json-params path-params] :as request}]
   (if-let [phrase (:phrase json-params)]
     (->> phrase
          controllers.search/search
          adapters.loan/->wire-document
          response-ok)
     (response-bad-request "Malformed request: Expected a JSON containing \"phrase\" key"))))