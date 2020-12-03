(ns aoc2020.day1
  (:require [aoc2020.util :as u]))


(defn summable?
 "When given nums are divided by 10, return true
  if reimainders of all sum to 10." 
  [& nums]
  (= 10
     (reduce + (map #(rem % 10) nums))))


(defn pair-of-2020 [expenses]
  (reduce 
   (fn [the-pair expense]
     (if the-pair
       (reduced the-pair)
       (let [; tidy up comparison set
             candidates (filter (partial summable? expense) expenses)
             match (->> candidates
                        (some
                         (fn [num]
                           (when (= 2020
                                    (+ expense num))
                             num))))]
         (when match
           [expense match]))))
   nil
   expenses))


(defn threes-of-2020 [expenses]
  (for [exp1 expenses
        exp2 expenses
        ; this "optimization" was making it slower, tenfold :facepalm:
        exp3 expenses #_(filter #(summable? exp1 exp2 %) expenses)
        :when (= 2020 
                 (+ exp1 exp2 exp3))]
    [exp1 exp2 exp3]))


(defn input-as-ints []
  (->> (u/input-as-lines "day1.txt")
       (map #(Integer/parseInt %))))


(defn part1
 "Specifically, they need you to find the two entries that
  sum to 2020 and then multiply those two numbers together."
  []
  (let [as-ints (input-as-ints)
        [num1 num2] (pair-of-2020 as-ints)]
    (when (and num1 num2)
      (int (* num1 num2)))))


(defn part2
 "find three numbers in your expense report that meet the same criteria" 
  []
  (let [expenses (input-as-ints)
        [num1 num2 num3 :as result] (first (threes-of-2020 expenses))]
    (when (every? integer? result)
      (int (* num1 num2 num3)))))


(comment 
  (time 
   (->> (u/input-as-lines "day1.txt")
        (map #(Integer/parseInt %))
        (threes-of-2020)
        (doall)))
  )
