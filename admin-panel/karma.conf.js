// Karma configuration file, see link for more information
// https://karma-runner.github.io/1.0/config/configuration-file.html

module.exports = function (config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine', '@angular/cli'],
    plugins: [
      require('karma-jasmine'),
      require('karma-chrome-launcher'),
      require('karma-jasmine-html-reporter'),
      require('karma-coverage-istanbul-reporter'),
      require('@angular/cli/plugins/karma')
    ],
    client:{
      clearContext: false // leave Jasmine Spec Runner output visible in browser
    },
    coverageIstanbulReporter: {
      reports: [ 'html', 'lcovonly' ],
      fixWebpackSourcePaths: true
    },
    angularCli: {
      environment: 'dev'
    },
    module: {
      loaders: [
        {
          test: path.join(__dirname, '.'),
          exclude: /(node_modules)/,
          loader: 'babel-loader',
          query: {
            cacheDirectory: true,
            presets: ['es2015', 'react']
          }
        },
        {
          test: /\.scss/,
          exclude: /node_modules/,
          loader: 'style!css?modules&importLoaders=2&sourceMap&localIdentName=[local]___[hash:base64:5]!autoprefixer?browsers=last 2 version!sass?outputStyle=expanded&sourceMap&includePaths[]=node_modules/compass-mixins/lib'
        },
        {
          test: /\.css$/,
          loader: 'style-loader!css-loader'
        },
        {
          test: /\.(png|jpg|jpeg|gif)$/,
          loader: 'url-loader?limit=10000'
        }, {
          test: /\.(wav|mp3)$/,
          loader: 'file-loader'
        },
        {
          test: /\.(eot|woff|woff2|ttf|svg)(\?\S*)?$/,
          loader: 'url?limit=100000@name=[name][ext]'
        }
      ]
    },
    reporters: ['progress', 'kjhtml'],
    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,
    autoWatch: true,
    browsers: ['Chrome'],
    singleRun: false
  });
};
