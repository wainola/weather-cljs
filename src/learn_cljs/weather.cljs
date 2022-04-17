(ns ^:figwheel-hooks learn-cljs.weather
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]))

(println "This text is printed from src/learn_cljs/weather.cljs. Go ahead and edit it and see reloading in action.")
(println "This is another text!!")

(defn multiply [a b] (* a b))
(def x 7)
(println "multiplication!" (multiply 2 3))
(println (+ x x))
(println "The result of the operation is: " (+ x x))
(defn greet [name] (str "Hello " name))
(println (greet "Nicolas"))

;; multi arity function
(defn messenger
([] (messenger "Hello world!"))
([msg] (println msg)))

(messenger "hi world")
(println messenger)

;; variadic functions
(defn hello [greeting & who]
(println greeting who))
(hello "hi" "mister" "anderson")

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "Live reloading is awesome!"}))
(defonce is-initialized? (
  do
    (.setItem js/localStorage "init-at" (.now js/Date))
    (js/alert "Welcome")
true))
(defonce my-state (atom {:text "my custom state"}))

(println (:text @app-state))

;; THIS DOES NOT SEPARATE THE LOGIC OF RECEIVING
;; A MESSSAGE AND DISPLAYING IT
(defn receive-message [text timestamp]
  (let [node (.createElement js/document "div")]
    (set! (.-innerHTML node) (str "[" timestamp "]: " text))
    (.appendChild messages-feed node))
)

(def input (.createElement js/document "input"))           ;; <2>
;; #'learn-cljs.weather/input                              ;; <3>

(.appendChild (.-body js/document) input)
;; #object[HTMLInputElement [object HTMLInputElement]]

(set! (.-placeholder input) "Enter something")             ;; <4>
;; "Enter something"

(defn handle-input [e]                                     ;; <5>
  (swap! app-state assoc :text (-> e .-target .-value)))
;; #'learn-cljs.weather/handle-input

(set! (.-onkeyup input) handle-input)

(defn get-app-element []
  (gdom/getElement "app"))

(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:h3 "Edit this in src/learn_cljs/weather.cljs and watch it change!"]
   [:h4 (:text @my-state)]])

(defn mount [el]
  (rdom/render [hello-world] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
