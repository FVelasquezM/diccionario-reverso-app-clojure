(ns diccionario-reverso-app-clojure.controllers.search
  (:require [diccionario-reverso-app-clojure.db.embedding :as db.embedding]
            [diccionario-reverso-app-clojure.db.vectorsearch :as db.vectorsearch]))

(defn search
  ;;TODO, how to imitate db injection like in Nazaré? Components?
  [phrase]
  (db.vectorsearch/get-closest (db.embedding/get-embedding phrase) 10))