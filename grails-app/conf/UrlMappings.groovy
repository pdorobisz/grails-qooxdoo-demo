import grails.util.Environment

class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')

        if (Environment.getCurrent().isDevelopmentMode()) {
            // This is required when we want to use same URL to refer qooxdoo's application js files in dev and prod.
            "/qx/$path**"(controller: "file") { root = "source" }

            "/qxapp/$root/$path**"(controller: "file")
        }
	}
}
