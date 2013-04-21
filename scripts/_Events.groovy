import grails.util.Environment

import java.nio.file.Paths

includeTargets << grailsScript("_GrailsEvents")

final def APP_DIR = "qooxdoo"
final def SRC_DIR = "$APP_DIR/build"
final def TARGET_DIR = "web-app/qx"

eventPackagingEnd = { warName ->
    if (Environment.getCurrent() == Environment.PRODUCTION) {
        def qooxdooPath = config.qooxdoo.sdk.path
        def generatorPath = Paths.get(qooxdooPath, "tool/bin/generator.py")
        AntBuilder ant = new AntBuilder()
        ant.exec(dir: APP_DIR, executable: "python") { arg(line: "-u $generatorPath -m QOOXDOO_PATH:$qooxdooPath build") }
        ant.delete(dir: TARGET_DIR)
        ant.move(todir: TARGET_DIR) { fileset(dir: SRC_DIR, excludes: "index.html") }
    }
}

eventCompileEnd = {
    ant.copy(file: "${basedir}/grails-app/conf/User-config.groovy", todir: classesDirPath)
    ant.copy(file: "${basedir}/grails-app/conf/App-config.groovy", todir: classesDirPath)
}