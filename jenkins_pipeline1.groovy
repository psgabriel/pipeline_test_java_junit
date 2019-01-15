node {
    stage 'Mat_Class_test'
    echo 'test'

    stage 'Mat_Class_checkstyle'
    echo 'check style'

    stage 'Mat_Class_verification'
    def e2e = build job:'Mat_Class_test', propagate: false
    result = e2e.result
    if (result.equals("SUCCESS")) {
        stage 'Mat_Class_deploy'
        build 'Mat_Class_pipeline_test+checkstyle_ok'
    } else {
        echo "Unexpected result on Check Mat_Class_test"
        build 'Mat_Class_pipeline_test+checkstyle_fail'
    }
}