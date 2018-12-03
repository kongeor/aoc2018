(ns aoc2018.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def input (string/split (slurp (io/resource "day2.txt")) #"\n"))

(defn id->freq [id]
  (map key (filter (fn [[k]] (> k 1))
                   (frequencies (vals (frequencies id))))))

;; q1
(comment
  (apply * (vals (frequencies (apply concat (map id->freq input))))))

(defn str-diff [s1 s2]
  (loop [[c1 & s1] s1
         [c2 & s2] s2
         [diff common] [0 []]]
    (if-not c1
      [diff (apply str common)]
      (recur s1 s2 (if (= c1 c2) [diff (conj common c1)] [(inc diff) common])))))

;; q2
(comment
  (time (first (sort-by first
                        (for [i1 input
                              i2 input
                              :when (not= i1 i2)]
                          (str-diff i1 i2))))))
