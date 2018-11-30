(ns adventofcode.2015.day6-test
  (:require [adventofcode.2015.day6 :refer :all]
            [clojure.test :refer :all]))

(deftest part1
  (let [board (make-board [10 10])]
    (is (= (change-board board turn-on 4 5 4 5)
        [[0 0 0 0 0 0 0 0 0 0]
         [0 0 0 0 0 0 0 0 0 0] 
         [0 0 0 0 0 0 0 0 0 0] 
         [0 0 0 0 0 0 0 0 0 0] 
         [0 0 0 0 1 1 0 0 0 0] 
         [0 0 0 0 1 1 0 0 0 0] 
         [0 0 0 0 0 0 0 0 0 0] 
         [0 0 0 0 0 0 0 0 0 0] 
         [0 0 0 0 0 0 0 0 0 0] 
         [0 0 0 0 0 0 0 0 0 0]]))
    (is (= (change-lights board "turn on 0,0 through 9,0" part2-ops)
        [[1 0 0 0 0 0 0 0 0 0]
         [1 0 0 0 0 0 0 0 0 0]
         [1 0 0 0 0 0 0 0 0 0]
         [1 0 0 0 0 0 0 0 0 0]
         [1 0 0 0 0 0 0 0 0 0]
         [1 0 0 0 0 0 0 0 0 0]
         [1 0 0 0 0 0 0 0 0 0]
         [1 0 0 0 0 0 0 0 0 0]
         [1 0 0 0 0 0 0 0 0 0]
         [1 0 0 0 0 0 0 0 0 0]]))
    (is (= (count-lights (change-lights board "turn on 0,0 through 9,0" part2-ops)) 10))))


;; > (let [board (make-board [1000 1000])]
;;                      (-> "day6-input.txt"
;;                          slurp-commands
;;                          (->> (reduce (fn [b command]
;;                                         (change-lights b command part1-ops)) board))
;;                          count-lights))

;; ==> 543903


;; > (let [board (make-board [1000 1000])]
;;    (-> "day6-input.txt"
;;        slurp-commands
;;        (->> (reduce (fn [b command]
;;                       (change-lights b command part2-ops)) board))
;;        total-brightness))
                       
;; ==> 14687245
