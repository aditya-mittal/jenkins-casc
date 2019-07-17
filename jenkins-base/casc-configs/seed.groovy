pipelineJob('pipeline/seedjob') {
    description("Auto-generated seed job")
    definition {
        cps {
            script ("""
                pipeline {
                    agent any
                    stages {
                        stage('process-job-dsls') {
                            steps {
                                jobDsl scriptText: '''
                                    pipelineJob('pipeline/seeded-pipeline-1') {
                                      definition {
                                        cps {
                                          script (\"""
                                            pipeline {
                                                agent any
                                                stages {
                                                    stage('build') {
                                                        steps {
                                                            sh script: 'echo "Build"'
                                                        }
                                                    }
                                                    stage('test') {
                                                        steps{
                                                            sh script : 'echo "Test"'
                                                        }
                                                    }
                                                    stage('publish') {
                                                        steps{
                                                            sh script : 'echo "Publish"'
                                                        }
                                                    }
                                                }
                                            }\""".stripIndent()
                                          )
                                        }
                                      }
                                    }
                                    pipelineJob('pipeline/seeded-pipeline-2') {
                                      definition {
                                        cps {
                                          script (\"""
                                            pipeline {
                                                agent any
                                                stages {
                                                    stage('build') {
                                                        steps {
                                                            sh script: 'echo "Build"'
                                                        }
                                                    }
                                                    stage('test') {
                                                        steps{
                                                            sh script : 'echo "Test"'
                                                        }
                                                    }
                                                    stage('publish') {
                                                        steps{
                                                            sh script : 'echo "Publish"'
                                                        }
                                                    }
                                                }
                                            }\""".stripIndent()
                                          )
                                        }
                                      }
                                    }
                                '''
                            }
                        }
                    }
                }
            """)
        }
    }
}