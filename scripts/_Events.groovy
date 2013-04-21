includeTargets << grailsScript("_GrailsEvents")

eventCompileEnd = {
    ant.copy(file: "${basedir}/grails-app/conf/User-config.groovy", todir: classesDirPath)
    ant.copy(file: "${basedir}/grails-app/conf/App-config.groovy", todir: classesDirPath)
}