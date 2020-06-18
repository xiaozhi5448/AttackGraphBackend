<template>
  <div id="app">
    <div class="container-fluid" id="top-container">
      <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light clearfix" id="navbar">
          <a class="navbar-brand" href="#">小型网络攻击图生成系统</a>
          <button
            class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>

          <div
            class="collapse navbar-collapse d-flex justify-content-end"
            id="navbarSupportedContent"
          >
            <form class="form-inline float-right">
              <div class="input-group">
                <div class="custom-file">
                  <input
                    type="file"
                    class="custom-file-input"
                    id="fileUploadINput"
                    aria-describedby="inputGroupFileAddon04"
                    @change="setLabel"
                  />
                  <label class="custom-file-label" for="inputGroupFile04">{{fileLabel}}</label>
                </div>
                <div class="input-group-append">
                  <button
                    class="btn btn-outline-secondary"
                    type="button"
                    id="inputGroupFileAddon04"
                    @click="uploadFile"
                  >提交</button>
                </div>
              </div>
            </form>
            <label for="goods-select-2" class="col-md-2">start:</label>
            <select
              class="form-control-sm col-md-2"
              id="goods-select-2"
              v-model="select_start_device"
            >
              <option v-for="(item, index) in startDevices" :key="index">{{item}}</option>
            </select>
          </div>
        </nav>
        <div class="message" v-if="message">
          <div class="alert alert-primary" role="alert">{{message}}</div>
        </div>
        <div class="plot-result">
          <div class="card">
            <div class="card-header">绘图结果</div>
            <div class="card-body">
              <div id="image" class="d-flex justify-content-center">
                <img v-if="!loading" id="image-comtent" v-bind:src="imageUrl" alt />
                <div v-else class="spinner-border text-primary" style></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*";
import cors from "cors";
export default {
  name: "App",
  data() {
    return {
      message: "",
      filename: "",
      imageUrl: "",
      loading: false,
      fileLabel: "选择文件",
      startDevices: [],
      select_start_device: ""
    };
  },

  methods: {
    setLabel: function(e) {
      console.log(e.target.files[0]["name"]);
      this.fileLabel = e.target.files[0]["name"];
      console.log(document.getElementById("fileUploadINput"));
    },
    uploadFile: function(e) {
      var fileTag = document.getElementById("fileUploadINput");
      if (fileTag["files"].length == 0) {
        return;
      }
      var formData = new FormData();
      formData.append("file", fileTag["files"][0]);
      let config = {
        // headers: { "Content-Type": "multipart/form-data" }
      };
      this.message = "文件解析中......";
      axios
        .post("http://localhost:9000/graph/upload", formData, config)
        .then(response => {
          console.log(response.data);
          this.startDevices = response.data;
          this.message = "文件已上传！";
        })
        .catch(error => {
          console.log(error);
          this.message = "上传文件出错";
        });
      // axios.post()
    },
    plotGraph: function(start) {
      this.loading = true;
      console.log(start);
      axios
        .get("http://127.0.0.1:9000/graph/data", {
          params: {
            startDeviceName: start
          }
        })
        .then(response => {
          this.message = "攻击图绘制完成";
          this.loading = false;
          console.log(response.data);
          this.imageUrl = response.data;
        });
    }
  },
  watch: {
    select_start_device: 'plotGraph'
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}
#top-container {
  background-color: rgb(248, 249, 250);
}
#navbar {
  width: 100%;
  padding-left: 0 !important;
  padding-right: 0 !important;
}
#image {
  float: none;
  display: inline-block;
  min-height: 500px;
  min-width: 700px;
  vertical-align: middle;
}
#image-content {
  display: block;
  width: 100%;
  height: 100%;
}
</style>
