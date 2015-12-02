(ns adventofcode.day2
  (:require [clojure.string :as s :refer [split-lines split]]
            [adventofcode.core :as core :refer [parse-int]]))

(defn slurp-package-sizes [inputfile]
  (-> inputfile
      slurp
      split-lines
      (->> (map (fn [sizestring]
                  (map parse-int
                       (split sizestring #"x")))))))

(defn calculate-paper-size [sizes]
  "calculate 2*l*w + 2*w*h + 2*h*l plus area of minimum side"
  (loop [sizes sizes
         cursize 0]
    (let [[[length width height] & restsizes] sizes
          lwsize (* 2 length width)
          whsize (* 2 width height)
          hlsize (* 2 height length)
          cursize (+ cursize
                     lwsize
                     whsize
                     hlsize)
          cursize (+ cursize (/ (min lwsize whsize hlsize) 2))]
      (if (empty? restsizes)
        cursize
        (recur restsizes cursize)))))

(defn calc-ribbon-length [sizes]
  "calculate smallest perimeters plus cubic volume"
  (loop [sizes sizes
         curlen 0]
    (let [[[length width height] & restsizes] sizes
          [mini medi maxi] (sort [length width height])
          perimeter (+ (* 2 mini)
                       (* 2 medi))
          volume (* length width height)
          curlen (+ curlen perimeter volume)]
      (if (empty? restsizes)
        curlen
        (recur restsizes curlen)))))
  
