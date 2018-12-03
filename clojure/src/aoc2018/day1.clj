(ns aoc2018.day1)

(defn parse-input []
  (map #(Integer/parseInt %)
     (clojure.string/split
       (slurp
         (clojure.java.io/resource "day1a.txt")) #"\n")))

;; q1
(comment
  (apply + (parse-input)))

(def input (vec (parse-input)))

(defn calc-freq [input]
  (loop [freqs #{}
         idx 0
         acc 0]
    (let [acc (+ acc (input idx))]
      (if (freqs acc)
        acc
        (recur (conj freqs acc)
               (mod (inc idx) (count input))
               acc)))))

;; q2
(comment
  (calc-freq input))
