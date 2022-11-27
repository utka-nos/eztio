pipeline{
    agent{
        label 'windows'
    }

    stages{

        stage('package'){
            steps{
                script{
                    bat '''
                        mvn clean package
                    '''
                }
            }
        }

        stage('deleting kubernetes objects') {
            steps{
                script{
                    bat '''
                        kubectl delete namespace eztio
                    '''
                }
            }
        }

        stage('deleting images') {
            steps{
               script{
                   try{
                       bat '''
                           minikube docker-env > temp.cmd
                           call temp.cmd
                           del temp.cmd

                           docker rmi product-app
                           docker rmi client-app
                       '''
                   }
               }
            }
        }

        stage('create images'){
            steps{
                script{
                    try{
                        bat '''
                            minikube docker-env > temp.cmd
                            call temp.cmd
                            del temp.cmd

                            docker build -t client-app ./client-service
                            docker build -t product-app .product-service
                        '''
                    }
                }
            }
        }

        stage('creating k8s objects'){
            steps{
                script{
                    try{
                        bat '''
                            minikube docker-env > temp.cmd
                            call temp.cmd
                            del temp.cmd

                            echo "==============================global objects========================================="

                            kubectl apply -f ./k8s/namespace.yml
                            kubectl apply -f ./prometheus-service/prometheus-role-sa.yml

                            echo "===============================secrets==============================================="

                            echo "==============================config maps============================================"

                            kubectl apply -f ./prometheus-service/prometheus-cm.yml


                            echo "==============================deployments============================================"

                            kubectl apply -f ./prometheus-service/prometheus-dc.yml

                            echo "==============================services==============================================="

                            kubectl apply -f ./prometheus-service/prometheus-svc.yml

                            echo "==============================others================================================="
                        '''
                    }
                }
            }
        }

    }

    post{
        always{
            cleanWs()
        }
    }

}