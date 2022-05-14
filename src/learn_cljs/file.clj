(println "This text is printed from src/learn_cljs/weather.cljs. Go ahead and edit it and see reloading in action.")
(println "This is another text!!")

(defn multiply [a b] (* a b))
(def x 7)
(println "multiplication!" (multiply 2 3))
(println (+ x x))
(println "The result of the operation is: " (+ x x))
(defn greet [name] (str "Hello " name))
(println (greet "Nicolas"))

(let [sides-list (list [4.2, 6] [4 4] [3 4] [5.5 3])]
  (for [sides sides-list]
    (
      Math.sqrt (+ (Math.pow (first sides) 2)
                   (Math.pow (second sides) 2))
    )))
