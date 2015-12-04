(ns adventofcode.day3-test
  (:require [adventofcode.day4 :refer :all]
            [clojure.test :refer :all]))

(deftest part1
  (is (= (min-postfix4five-zero-md5 "abcdef") 609043))
  (is (= (min-postfix4five-zero-md5 "pqrstuv") 1048970)))

(deftest part2
  (is (= (min-postfix4five-zero-md5 "yzbqklnj" :zerocount 6) 9962624)))

