(ns adventofcode.2015.day4
  (:require [pandect.algo.md5 :as md5]))

(defn calc-hash [prefix number]
  (md5/md5 (str prefix number)))

(defn  has-zero-prefix? [string zerocount]
  (.startsWith string (apply str (repeat zerocount 0))))

(defn min-postfix4five-zero-md5 [string & {:keys [zerocount] :or {zerocount 5}}]
  (loop [string string
         number 1]
    (let [hash (calc-hash string number)]
      (if (has-zero-prefix? hash zerocount)
        number
        (recur string (inc number))))))
