window.onload = function() {
  //<editor-fold desc="Changeable Configuration Block">

  // the following lines will be replaced by docker/configurator, when it runs in a docker-container
  window.ui = SwaggerUIBundle({
    urls: [
        {
            url: "api.yaml",
            name: "tms"
        }
    ],
    "urls.primaryName": "tms",
    dom_id: '#swagger-ui',
    defaultModelsExpandDepth: 10,
    defaultModelExpandDepth: 10, // 이 설정도 함께 사용하는 것이 좋습니다.
    deepLinking: true,
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    plugins: [
      SwaggerUIBundle.plugins.DownloadUrl
    ],
    layout: "StandaloneLayout"
  });

  //</editor-fold>
};
