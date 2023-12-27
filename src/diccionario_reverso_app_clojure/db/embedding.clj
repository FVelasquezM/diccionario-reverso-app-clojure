(ns diccionario-reverso-app-clojure.db.embedding
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [dotenv :refer [env]]))
;; TODO, would it be a better practice to call this database by the name of the provider (OpenAI) instead of its function? (Convert strings to vector embeddings)

;;TODO, http client injection? credential injection? how could I imitate the db injection used in Nazare?

;TODO, define embedding schema
(defn- entity->embedding
  [response-body]
  ((first (response-body "data")) "embedding"))
;;TODO, improvement. Reuse http client or use connection pool.
(defn get-embedding
  "Returns a 1536-dimensional embedding for a given phrase using Open AI's text-embedding-ada-002 model"
  [phrase]
  (let [embedding-response (json/read-str
                             (:body (client/post (env :OPENAI-EMBEDDING-ROUTE)
                                                 {:content-type :json
                                                  :headers      {"Authorization" (format "Bearer %s" (env :OPENAI-API-KEY))
                                                                 "Content-Type"  "application/json"}
                                                  :body         (format "{\"input\": \"%s\",\"model\": \"text-embedding-ada-002\"}" phrase)})))]
    (entity->embedding embedding-response)))