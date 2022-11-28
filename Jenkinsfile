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
                    try{
                        bat '''
                            kubectl delete namespace eztio
                        '''
                    }
                    catch(err) {
                        err.getMessage()
                    }
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
                    catch(err) {
                        err.getMessage()
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
                            docker build -t product-app ./product-service
                        '''
                    }
                    catch(err) {
                        err.getMessage()
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
                            kubectl apply -f ./k8s/ingress/ingress-rb.yml

                            echo "===============================secrets==============================================="

                            kubectl apply -f ./product-service/db-secret.yml

                            echo "==============================config maps============================================"

                            kubectl apply -f ./prometheus-service/prometheus-cm.yml

                            echo "==============================deployments============================================"

                            kubectl apply -f ./prometheus-service/prometheus-dc.yml
                            kubectl apply -f ./product-service/product-dc.yml
                            kubectl apply -f ./k8s/ingress/ingress-dc.yml

                            echo "==============================services==============================================="

                            kubectl apply -f ./prometheus-service/prometheus-svc.yml
                            kubectl apply -f ./product-service/product-svc.yml
                            kubectl apply -f ./k8s/ingress/ingress-svc.yml

                            echo "==============================others================================================="

                            kubectl apply -f ./product-service/product-vs.yml
                            kubectl apply -f ./product-service/product-gw.yml
                            kubectl apply -f ./prometheus-service/prometheus-gw.yml
                            kubectl apply -f ./prometheus-service/prometheus-vs.yml

                        '''
                    }
                    catch(err) {
                        echo err.getMessage()
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