(ns dmml-lab-site.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [schema.core :as sc]
            [schema.macros :as sm]
            [clostache.parser :refer [render]]))

(sm/defn last-name :- sc/Str
  [name :- sc/Str]
  (-> name (str/split #"\s+") last))

;; TODO: data that can later be put in external files.
(def faculty (sort-by (comp last-name :name)
                      [{:homepage "http://cs.gmu.edu/~dbarbara/"
                        :name "Daniel Barbara"
                        :interests nil
                        :email ""}
                       {:homepage "http://cs.gmu.edu/~carlotta/students/"
                        :name "Carlotta Domeniconi"
                        :interests nil
                        :email ""}
                       {:homepage "http://www.cs.gmu.edu/~jessica/"
                        :name "Jessica Lin"
                        :interests nil
                        :email ""}
                       {:homepage "http://www.cs.gmu.edu/~hrangwal/"
                        :name "Huzefa Rangwala"
                        :interests nil
                        :email ""}]))

(def phd-students (sort-by (comp last-name :name)
                           [{:homepage "http://gmu.academia.edu/MattRevelle"
                             :name "Matt Revelle"
                             :interests nil
                             :email ""}
                            {:homepage "#"
                             :name "David Etter"
                             :interests nil
                             :email ""}
                            {:homepage "#"
                             :name "James Rogers"
                             :interests nil
                             :email ""}
                            {:homepage "#"
                             :name "Rohan Khade"
                             :interests nil
                             :email ""}
                            {:homepage "http://gmu.academia.edu/TanwisthaSaha"
                             :name "Tanwistha Saha"
                             :interests nil
                             :email ""}
                            {:homepage "http://mason.gmu.edu/~rotunba/"
                             :name "Rasaq Otunba"
                             :interests nil
                             :email ""}
                            {:homepage "#"
                             :name "Loulwah S Al-Sumait"
                             :interests nil
                             :email ""}
                            {:homepage "#"
                             :name "Xing Wang"
                             :interests nil
                             :email ""}]))

(def ms-students [])
(def ug-students [])

(def visiting-scholars (sort-by (comp last-name :name)
                                [{:homepage nil
                                  :name "Yazhou Ren"
                                  :interests nil
                                  :email ""}]))

(def alumni (sort-by :year >
                     [{:homepage nil
                       :name "Sam Blasiak"
                       :year 2013
                       :currently nil}]))

(def projects {})
(def publications {})
(def datasets {})
(def courses {})

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
            "people.html" (main-page :people (render (template "people.mustache") {:faculty faculty
                                                                                   :phd-students phd-students
                                                                                   :ms-students ms-students
                                                                                   :ug-students ug-students
                                                                                   :visiting-scholars visiting-scholars
                                                                                   :alumni alumni}))
            "projects.html" (main-page :projects "")
            "publications.html" (main-page :publications "")
            "code.html" (main-page :code "")
            "datasets.html" (main-page :datasets "")
            "courses.html" (main-page :related-courses "")})

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
  [& args])
