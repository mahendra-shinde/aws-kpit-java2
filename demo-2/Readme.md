# S3 Upload Demo

1. Right click on `project-name` in package explorer or project explorer panel.
2. Choose option "Run As" then "Maven Build"
    When this option is used for FIRST TIME, you need to enter "goal" as
    package. and keep "profile" option empty

    second time onwards, no need to enter goal and profile.
3. Right click on project name once again, this time use option `Show in local terminal` 

4. In terminal, use following commands to locate the JAR file

    ```cmd
    cd target
    dir
    ```

5.  Run the application JAR. For NAME-OF-JAR, refer to response from "dir" command

    ```
    java -jar NAME-OF-JAR "c:\\data.txt"
    ```
