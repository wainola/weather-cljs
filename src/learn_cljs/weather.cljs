(ns ^:figwheel-hooks learn-cljs.weather
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]))

(defn hello-world []
  [:div
    [:h1 {:class "app-title"} "Hello world"]])

(defn mount-app-element [] 
  (rdom/render [hello-world] (gdom/getElement "app")))

(def a 42)
(def aa (cond
  (< a 10) "a is less than 10"
  (= a 10) "a is 10"
  (> a 10) "a is bigger than 10"
  :else "a is not a number!"))

(println aa)
(cond
  (= 2 3) (println "two is equal to three")
  (= 2 2) (println "two is equal to two")
  :else (println "nothing to say"))
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
