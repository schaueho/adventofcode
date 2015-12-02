(ns adventofcode.core
  (:require [clojure.string :as s :refer [split-lines split]]))

(defn parse-int [s]
  (Integer/parseInt (re-find #"\A-?\d+" s)))

