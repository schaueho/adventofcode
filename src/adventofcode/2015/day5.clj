(ns adventofcode.2015.day5
  (:require [clojure.string :as s :refer [split-lines]]))


(defn slurp-words [inputfile]
  (-> inputfile
      slurp
      split-lines))

(def vowels #{\a \e \i \o \u})

(def naughty-pairs {\a \b \c \d \p \q \x \y})

(defn nice-word-helper
  [[curchar & restword] lastchar seenvowels twicearow]
  (if (= (get naughty-pairs lastchar) curchar)
    false
    (if (get vowels curchar)
      (if (seq? restword)
        (recur restword curchar (conj seenvowels curchar) (or twicearow (= lastchar curchar)))
        (if (and (or twicearow
                     (= lastchar curchar))
                 (>= (count (conj seenvowels curchar)) 3))
          true
          (and (or twicearow (= lastchar curchar)) (>= (count (conj seenvowels curchar)) 3))))
      (if (seq? restword)
        (recur restword curchar seenvowels (or twicearow (= lastchar curchar)))
        (and (or twicearow (= lastchar curchar)) (>= (count seenvowels) 3))))))

(defn nice-word? [word]
  (if (nice-word-helper word nil [] false)
    true
    false))

(defn count-nice-words [inputfile]
  (-> inputfile
      slurp-words
      (->> (filter nice-word?))
      count))

(defn part2-nice [word]
  (when (re-find #"(..).*\1" word)
    (when (re-find #"(.).\1" word)
      true)))

            
         

