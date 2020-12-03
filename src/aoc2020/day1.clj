(ns aoc2020.day1
  (:require [aoc2020.util :as u]))


(defn summable?
 "When num1 and num2 are divided by 10, return true
  if reimainders of both sum to 10." 
  [num1 num2]
  (= 10
     (+ (rem num1 10)
        (rem num2 10))))


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


(defn part1
 "Specifically, they need you to find the two entries that 
  sum to 2020 and then multiply those two numbers together."
  []
  (let [as-ints (->> (u/input-as-lines "day1.txt")
                     (map #(Integer/parseInt %)))
        [num1 num2] (pair-of-2020 as-ints)]
    (when (and num1 num2)
      (int (* num1 num2)))))


(comment 
  (time 
   (->> (u/input-as-lines "day1.txt")
        (map #(Integer/parseInt %))
        (pair-of-2020)))
  )
