(ns adventofcode.2015.day6
  (:require
   [clojure.string :as string :refer [split-lines]]
   [com.rpl.specter :as s :refer :all]))

(defn slurp-commands [inputfile]
  (-> inputfile
      slurp
      split-lines))

(defn make-board [[m n]]
  (into []
        (repeatedly m
                    #(into [] (take n (repeat 0))))))

(defn print-board [board]
  (doseq [line board]
    (println line)))

(defn toggle [zero-or-one]
  (condp = zero-or-one
    0 1
    1 0
    (throw (ex-info "Unexpected value" zero-or-one))))

(defn turn-on [_]
  1)

(defn turn-off [_]
  0)

(defn change-board [board operation x-start x-end y-start y-end]
  ;; input data is inclusive for all positions, 
  ;; but specter's srange is (inclusive exclusive)
  ;; so we have to inc the second value
  (transform [(srange x-start (inc x-end)) ALL (srange y-start (inc y-end)) ALL]
             operation
             board))


(defn count-lights [board]
  (-> (select [ALL ALL #(= % 1)] board)
      count))

;; part 2
(defn turn2-on [pos]
  (inc pos))

(defn turn2-off [pos]
  (max 0 (dec pos)))

(defn toggle2 [pos]
  (+ pos 2))

(def part1-ops
  {"toggle" toggle
   "turn on" turn-on
   "turn off" turn-off})

(def part2-ops
  {"toggle" toggle2
   "turn on" turn2-on
   "turn off" turn2-off})

(defn change-lights [board command opmap]
  ;; toggle 461,550 through 564,900
  ;; turn off 370,39 through 425,839
  (when-let [parsed (re-find #"(toggle|turn on|turn off) (\d+),(\d+) through (\d+),(\d+)" command)]
    (let [[_ opstring xst yst xe ye] parsed
          x-start (Integer. xst)
          x-end (Integer. xe)
          y-start (Integer. yst)
          y-end (Integer. ye)
          operation (get opmap opstring)]
      (change-board board operation x-start x-end y-start y-end))))

(defn total-brightness [board]
  (apply + (select [ALL ALL] board)))
  
