(ns diccionario-reverso-app-clojure.db.vectorsearch
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [dotenv :refer [env]]))

;; TODO, would it be a better practice to call this database by the name of the provider (Pinecone) instead of its function? (Search closest vectors in a vector space)

(defn- entity->result-entry
  [response-body]
  (map (fn [res] {:score      (res "score")
                  :word       ((res "metadata") "word")
                  :definition ((res "metadata") "definition")}) (response-body "matches")))

;;TODO, improvement. Reuse http client or use connection pool. Would components be appropiate for this?
(defn get-closest
  [embedding n]
  (let [response (json/read-str
                   (:body (client/post (env :PINECONE-QUERY-ROUTE)
                                       {:content-type :json
                                        :headers      {"Api-Key" (env :PINECONE-API-KEY)}
                                        :body         (json/write-str {:vector          embedding
                                                                       :topK            n
                                                                       :includeMetadata true
                                                                       :includeValues   true})})))]
    (entity->result-entry response)))