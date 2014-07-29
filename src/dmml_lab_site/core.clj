(ns dmml-lab-site.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [schema.core :as sc]
            [schema.macros :as sm]
            [clostache.parser :refer [render]]
            [dmml-lab-site.data :as data]))

;; TODO: could be configurable
(def templates-dir (io/file "./templates"))

(sm/defn template :- sc/Str
  [name :- sc/Str]
  (slurp (io/file templates-dir name)))

(sm/defn beautify :- sc/Str
  [x :- (sc/either sc/Keyword sc/Str)]
  (-> x name (str/replace "-" " ") str/capitalize))

;; TODO: pull out list of page names and paths, use to generate
;; nav-pages local and pages global
(sm/defn main-page :- sc/Str
  "Renders a top-level page of the site using the default main layout."
  [active-page :- sc/Keyword
   content :- sc/Str]
  (let [nav-pages (map (fn [[page path]]
                         {:title (beautify page)
                          :path path
                          :active (= page active-page)})
                       [[:home "index.html"]
                        [:people "people.html"]
                        [:projects "projects.html"]
                        [:publications "publications.html"]
                        [:code "code.html"]
                        [:datasets "datasets.html"]
                        [:courses "courses.html"]])]
    (render (template "main.mustache") {:nav-pages nav-pages
                                         :content content})))

(def pages {"index.html" (main-page :home (render (template "home.mustache")))
            "people.html" (main-page :people (render (template "people.mustache")
                                                     {:faculty data/faculty
                                                      :phd-students data/phd-students
                                                      :ms-students data/ms-students
                                                      :ug-students data/ug-students
                                                      :visiting-scholars data/visiting-scholars
                                                      :alumni data/alumni}
                                                     {:profile (template "profile.mustache")}))
            "projects.html" (main-page :projects (render (template "projects.mustache")))
            "publications.html" (main-page :publications (render (template "publications.mustache")))
            "code.html" (main-page :code (render (template "code.mustache")))
            "datasets.html" (main-page :datasets (render (template "datasets.mustache")))
            "courses.html" (main-page :courses (render (template "courses.mustache")
                                                       {:courses data/courses}))})

(def css-files (rest (file-seq (io/file "css"))))
(def img-files (rest (file-seq (io/file "img"))))
(def js-files (rest (file-seq (io/file "js"))))
(def font-files (rest (file-seq (io/file "fonts"))))

;; TODO: currently depends on resources not being nested
(def resources (apply merge (map (fn [[out-dir files]]
                                   (into {} (map (comp vec (partial repeat 2)) files)))
                                 {(io/file "css") css-files
                                  (io/file "img") img-files
                                  (io/file "js") js-files
                                  (io/file "fonts") font-files})))

;; TODO: common mapping schema for pages and resources?
(sm/defn generate-site
  [pages :- {sc/Str sc/Str}
   resources :- {java.io.File java.io.File}
   out-dir :- java.io.File]
  (doseq [f (map (partial io/file out-dir) (concat (keys pages) (keys resources)))]
    (io/make-parents f))
  (doseq [[name content] pages]
    (spit (io/file out-dir name) content))
  (doseq [[dst src] resources]
    (io/copy src (io/file out-dir dst))))

(defn -main
  [& args]
  (println "Generating site...")
  (generate-site pages resources (io/file "dmml_lab_site"))
  (println "Site generated."))
