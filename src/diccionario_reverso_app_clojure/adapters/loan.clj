(ns diccionario-reverso-app-clojure.adapters.loan)

(defn ->wire-document
  "Does a small, document to wire conversion for no practical reason, intended simply as test of the diplomat architecture's adapter."
  [results]
  {:results results
   :origin "Clojure Server"})