<template>
  <a-layout id="components-layout-demo-top-side">
    <a-layout-header class="header">
      <section data-v-cd3c4ad0="" class="main-header">
        <div data-v-cd3c4ad0="" class="header-icon">
          <img
            data-v-cd3c4ad0=""
            src="//p0.meituan.net/scarlett/c6db30a6a1a04c9352e9e9e97bf22250208028.png"
            class="header-icon-img"
          />
        </div>
        <span data-v-cd3c4ad0="" class="header-title">学 科 知 识 流 动 分 析 可 视 化 界 面</span>
        <div data-v-cd3c4ad0="" class="header-info">
          <div data-v-cd3c4ad0="" class="header-info-doc">
            <a data-v-cd3c4ad0="" class="doc-dsec text">文档</a><a data-v-cd3c4ad0="" class="doc-feedback text">反馈</a>
          </div>
          <img
            data-v-cd3c4ad0=""
            src="https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2022894887,995087717&fm=26&gp=0.jpg"
            class="header-info-user"
          />
        </div>
      </section>
    </a-layout-header>
    <a-layout-content>
      <a-layout style="padding: 24px 0; background: #fff">
        <a-layout-sider width="200" style="background: #fff">
          <a-menu mode="inline" :default-selected-keys="['1']" :default-open-keys="['sub1']" style="height: 100%">
            <a-sub-menu key="sub1" @click="subClick">
              <span slot="title"><a-icon type="user" />学科属性查询</span>
              <a-menu-item key="1"> 发文量 </a-menu-item>
              <a-menu-item key="2"> 引文量 </a-menu-item>
              <a-menu-item key="3"> 被引量 </a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="sub2" @click="subClick">
              <span slot="title"><a-icon type="laptop" />学科特性查询</span>
              <a-menu-item key="5"> 知识吸收能力 </a-menu-item>
              <a-menu-item key="6"> 知识扩散能力 </a-menu-item>
              <a-menu-item key="7"> 知识创新能力</a-menu-item>
              <a-menu-item key="8"> 知识流动速度</a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="sub3" @click="subClick">
              <span slot="title"><a-icon type="notification" />学科知识流动</span>
              <a-menu-item key="9"> 知识吸收变化 </a-menu-item>
              <a-menu-item key="10"> 知识扩散变化 </a-menu-item>
              <a-menu-item key="11"> 知识流动情况 </a-menu-item>
            </a-sub-menu>
          </a-menu>
        </a-layout-sider>
        <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
          <div>
            <div class="all">
              <a-button>All</a-button>
              <div style="margin-right: 10px">&nbsp;</div>
              <a-tag color="#108ee9"> Multidisciplinary </a-tag>
            </div>
            <div class="top">
              <a-tag color="#2db7f5" class="btn btn-info" type="button">Medicine</a-tag>
              <a-tag color="#2db7f5" class="btn btn-info" type="button">Biochemistry</a-tag>
              <a-tag color="#2db7f5" class="btn btn-info" type="button">Immunology and Microbiology</a-tag>
              <a-tag color="#2db7f5" class="btn btn-info" type="button">Neuroscience</a-tag>
              <a-tag color="#2db7f5" class="btn btn-info" type="button">Pharmacology</a-tag>
              <a-tag color="#2db7f5" class="btn btn-info" type="button">Nursing</a-tag>
              <a-tag color="#2db7f5" class="btn btn-info" type="button">Dentistry</a-tag>
              <a-tag color="#2db7f5" class="btn btn-info" type="button">Veterinary</a-tag>
              <a-tag color="#2db7f5" class="btn btn-info" type="button">Health Professions</a-tag>
            </div>
            <div class="top">
              <a-tag color="#87d068" class="btn btn-success" type="primary"
                >Agricultural and Biological Sa-buttons</a-tag
              >
              <a-tag color="#87d068" class="btn btn-success" type="primary">Environmental Science</a-tag>
              <a-tag color="#87d068" class="btn btn-success" type="primary">Earth and Planetary Sciences</a-tag>
            </div>

            <div class="top">
              <a-tag color="#fa8c16" class="btn btn-warning" type="button">Chemistry</a-tag>
              <a-tag color="#fa8c16" class="btn btn-warning" type="button">Engineering</a-tag>
              <a-tag color="#fa8c16" class="btn btn-warning" type="button">Materials Science</a-tag>
              <a-tag color="#fa8c16" class="btn btn-warning" type="button">Physics and Astronomy</a-tag>
              <a-tag color="#fa8c16" class="btn btn-warning" type="button">Chemical Engineering</a-tag>
              <a-tag color="#fa8c16" class="btn btn-warning" type="button">Energy</a-tag>
              <a-tag color="#fa8c16" class="btn btn-warning" type="button">Computer Science</a-tag>
              <a-tag color="#fa8c16" class="btn btn-warning" type="button">Mathematics</a-tag>
            </div>

            <div class="top">
              <a-tag color="#f50" class="btn btn-danger" type="button">Social Sciences</a-tag>
              <a-tag color="#f50" class="btn btn-danger" type="button">Psychology</a-tag>
              <a-tag color="#f50" class="btn btn-danger" type="button">Business, Management and Accounting</a-tag>
              <a-tag color="#f50" class="btn btn-danger" type="button">Economics, Econometrics and Finance</a-tag>
              <a-tag color="#f50" class="btn btn-danger" type="button">Arts and Humanities</a-tag>
              <a-tag color="#f50" class="btn btn-danger" type="button">Decision Sciences</a-tag>
            </div>

            <div>
              <template v-if="!showImageUrl2">
                <img id="imagepath" :src="showImageUrl" style="display: block; max-width: 1400px" />
              </template>
              <template v-else>
                <img id="imagepath" :src="showImageUrl2" style="display: block; max-width: 1000px" />
                <img id="imagepath" :src="showImageUrl2" style="display: block; max-width: 1000px" />
              </template>
            </div>
          </div>
        </a-layout-content>
      </a-layout>
    </a-layout-content>
  </a-layout>
</template>
<script>
export default {
  name: 'App',
  components: {},
  data() {
    return {
      image3260: require('../assets/326/0.png'),
      image0: require('../assets/326/0.png'),
      image1: require('../assets/326/1.png'),
      image2: require('../assets/326/2.png'),

      image3: require('../assets/326/3.png'),
      image4: require('../assets/326/4.png'),
      image5: require('../assets/326/5.png'),
      image6: require('../assets/326/6.png'),

      image7: require('../assets/326/7.png'),
      image71: require('../assets/326/7-1.png'),
      image8: require('../assets/326/8.png'),
      image81: require('../assets/326/8-1.png'),
      image9: require('../assets/326/9.png'),
      image10: require('../assets/326/10.png'),
      image91: require('../assets/326/9-1.png'),
      image92: require('../assets/326/9-2.png'),
      image93: require('../assets/326/9-3.png'),
      showImageUrl: require('../assets/326/0.png'),
      showImageUrl2: '',
      showImageUrl3: '',
    }
  },
  methods: {
    subClick({ item, key, keyPath }) {
      this.showImageUrl2 = ''
      console.log('{ item, key, keyPath }', item, key, keyPath)
      this.showImageUrl = this[`image${key - 1}`]
      if (this[`image${key - 1}1`]) {
        this.showImageUrl2 = this[`image${key - 1}1`]
      }
      if (this[`image${key - 1}2`]) {
        this.showImageUrl2 = this[`image${key - 1}2`]
      }
    },
  },
}
</script>


<style>
* {
  margin: 0;
  padding: 0;
}
p {
  margin: 0;
}
#components-layout-demo-top-side .logo {
  width: 120px;
  height: 31px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px 28px 16px 0;
  float: left;
}
.main-header .header-icon .header-icon-img {
  height: 35px;
  -o-object-fit: cover;
  object-fit: cover;
}
.main-header .header-info {
  -webkit-box-flex: 1;
  -ms-flex: 1;
  flex: 1;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: end;
  -ms-flex-pack: end;
  justify-content: flex-end;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
}
.header-info .header-info-doc {
  font-size: 14px;
  color: #fff;
}
.main-header .header-info .header-info-user {
  margin-left: 27px;
  height: 35px;
  width: 35px;
  border-radius: 17.5px;
}
.main-header .header-info .header-info-doc a {
  color: #fff;
  cursor: pointer;
  text-decoration: none;
}
.header-info .header-info-doc .doc-feedback[data-v-cd3c4ad0] {
  margin-left: 27px;
}
.main-header .header-title {
  margin-left: 20px;
  font-weight: 700;
  font-size: 20px;
  color: #f0f0f0;
}
.main-header {
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  background: #293553;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  padding: 0 25px;
  -webkit-box-shadow: 0 2px 4px rgb(0 0 0 / 12%), 0 0 6px rgb(0 0 0 / 4%);
  box-shadow: 0 2px 4px rgb(0 0 0 / 12%), 0 0 6px rgb(0 0 0 / 4%);
  z-index: 11;
}
.ant-layout-content {
  flex: none !important;
}
.all {
  display: flex;
  align-items: center;
}
.top {
  margin-top: 10px;
  display: flex;
}
.btn {
  margin-right: 10px;
}

.ant-tag {
  padding: 3px 8px !important;
  font-size: 16px !important;
}
</style>
