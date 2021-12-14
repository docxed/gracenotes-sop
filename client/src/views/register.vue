<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
      <div class="container">
        <a class="navbar-brand" href="/">
          <img
            src="../assets/logo.png"
            alt=""
            width="40"
            height="40"
            class="d-inline-block align-center"
          />
          Gracenotes
        </a>
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" href="/">ลงชื่อเข้าใช้งาน</a>
          </li>
        </ul>
      </div>
    </nav>
    <br /><br /><br />

    <div class="container">
      <div class="content">
        <h3>ลงทะเบียนเข้าใช้งาน</h3>
        <br />
        <label for="user">รหัสนักเรียน</label>
        <input
          type="text"
          v-model="$v.user.$model"
          :class="{ 'is-danger text-danger': $v.user.$error }"
          class="form-control"
          placeholder="รหัสนักเรียน"
          name="user"
          required
        />
        <template v-if="$v.user.$error">
          <p class="help text-danger" v-if="!$v.user.required">
            This field is required
          </p>
          <p class="help text-danger" v-if="!$v.user.user || !$v.user.numeric">
            Please insert user ID correctly (8-10 digits)
          </p>
          <p class="help text-danger" v-if="!$v.user.maxLength">
            This field can contain only 10 digits
          </p>
        </template>
        <div class="row g-2">
          <div class="col">
            <label for="fname">ชื่อ</label>
            <input
              type="text"
              v-model="$v.fname.$model"
              :class="{ 'is-danger text-danger': $v.fname.$error }"
              class="form-control"
              placeholder="ชื่อ"
              name="fname"
              required
            />
            <template v-if="$v.fname.$error">
              <p class="help text-danger" v-if="!$v.fname.required">
                This field is required
              </p>
              <p class="help text-danger" v-if="!$v.fname.maxLength">
                This field can contain only 30 characters
              </p>
              <p class="help text-danger" v-if="!$v.fname.fullname">
                No numeric allowed
              </p>
            </template>
          </div>
          <div class="col">
            <label for="lname">นามสกุล</label>
            <input
              type="text"
              v-model="$v.lname.$model"
              :class="{ 'is-danger text-danger': $v.lname.$error }"
              class="form-control"
              placeholder="นามสกุล"
              name="lname"
              required
            />
            <template v-if="$v.lname.$error">
              <p class="help text-danger" v-if="!$v.lname.required">
                This field is required
              </p>
              <p class="help text-danger" v-if="!$v.lname.maxLength">
                This field can contain only 30 characters
              </p>
              <p class="help text-danger" v-if="!$v.lname.fullname">
                No numeric allowed
              </p>
            </template>
          </div>
        </div>
        <label for="class">ชั้นมัธยมศึกษาปีที่</label>
        <input
          type="text"
          v-model="$v.classes.$model"
          :class="{ 'is-danger text-danger': $v.classes.$error }"
          class="form-control"
          placeholder="ชั้นมัธยมศึกษาปีที่ ตัวอย่าง 6/6"
          name="class"
          maxlength="3"
          required
        />
        <template v-if="$v.classes.$error">
          <p class="help text-danger" v-if="!$v.classes.required">
            This field is required
          </p>
          <p class="help text-danger" v-if="!$v.classes.classes">
            Please Insert class correctly (class 1-8 only)
          </p>
        </template>
        <label for="no">เลขที่</label>
        <input
          type="text"
          v-model="$v.no.$model"
          :class="{ 'is-danger text-danger': $v.no.$error }"
          class="form-control"
          placeholder="เลขที่ เช่น 5, 34"
          name="no"
          min="1"
          max="99"
          required
        />
        <template v-if="$v.no.$error">
          <p class="help text-danger" v-if="!$v.no.required">
            This field is required
          </p>
          <p
            class="help text-danger"
            v-if="!$v.no.numeric || !$v.no.maxLength || !$v.no.no3"
          >
            Please Insert number correctly (2 digits)
          </p>
          <p class="help text-danger" v-if="!$v.no.no2">
            Limit number at 60 only
          </p>
        </template>
        <label for="dob">วัน/เดือน/ปี เกิด</label>
        <input
          type="date"
          v-model="$v.dob.$model"
          :class="{ 'is-danger text-danger': $v.dob.$error }"
          class="form-control"
          name="dob"
          required
        />
        <template v-if="$v.dob.$error">
          <p class="help text-danger" v-if="!$v.dob.required">
            This field is required
          </p>
          <p class="help text-danger" v-if="!$v.dob.maxValue">
            Please Insert Date correctly
          </p>
        </template>
        <label for="address1">ที่อยู่</label>
        <input
          type="text"
          v-model="$v.address1.$model"
          :class="{ 'is-danger text-danger': $v.address1.$error }"
          class="form-control"
          name="address1"
          placeholder="ที่อยู่ 1"
          required
        />
        <template v-if="$v.address1.$error">
          <p class="help text-danger" v-if="!$v.address1.required">
            This field is required
          </p>
        </template>
        <input
          type="text"
          v-model="address2"
          class="form-control"
          name="address2"
          placeholder="ที่อยู่ 2 (ไม่บังคับ)"
          style="margin-top: 10px"
        />
        <br />
        <div v-for="image in images" :key="image.id">
          <p class="text-center">
            <img
              :src="showSelectImage(image)"
              alt="Placeholder image"
              class="img-fluid col-lg-3 col-md-5 col-sm-12"
            />
          </p>
        </div>
        <label for="img">รูปถ่ายนักเรียนแนวตั้ง</label><br />
        <input
          type="file"
          name="img"
          id="files"
          accept="image/png, image/jpeg, image/webp"
          @change="selectImages"
          required
          multiple
        />
        <template v-if="$v.images.$error">
          <p class="help text-danger" v-if="!$v.images.images">
            Must Upload The picture
          </p>
        </template>
        <br />
        <label for="pass">รหัสผ่าน</label>
        <input
          type="password"
          v-model="$v.pass.$model"
          :class="{ 'is-danger text-danger': $v.pass.$error }"
          class="form-control"
          placeholder="รหัสผ่าน"
          name="pass"
          maxlength="15"
          required
        />
        <template v-if="$v.pass.$error">
          <p class="help text-danger" v-if="!$v.pass.required">
            This field is required
          </p>
          <p class="help text-danger" v-if="!$v.pass.pass">
            Password must be harder (include Capital letter, Alphabet ,number)
          </p>
          <p class="help text-danger" v-if="!$v.pass.minLength">
            Password must contain at least 5 character
          </p>
        </template>
        <label for="repass">ยืนยันรหัสผ่าน</label>
        <input
          type="password"
          v-model="$v.repass.$model"
          :class="{ 'is-danger text-danger': $v.repass.$error }"
          class="form-control"
          placeholder="ยืนยันรหัสผ่าน"
          name="repass"
          maxlength="15"
          required
        />
        <template v-if="$v.repass.$error">
          <p class="help text-danger" v-if="!$v.repass.required">
            This field is required
          </p>
          <p class="help text-danger" v-if="!$v.repass.repass">
            Muse be the same as password
          </p>
        </template>
        <br />
        <div class="text-center">
          <input
            v-show="btnShow"
            type="submit"
            class="btn btn-success"
            value="ลงทะเบียน"
            @click="validate()"
          />
        </div>
      </div>
    </div>
    <br /><br />
  </div>
</template>


<script>
import {
  required,
  maxLength,
  minLength,
  sameAs,
  numeric,
} from "vuelidate/lib/validators";
import axios from "axios";
import firebase from "firebase";

function user(value) {
  return !!value.match(/[1-9]{1}[0-9]{7}/);
}
function classes(value) {
  return !!value.match(/[1-6]{1}[/]{1}[1-8]{1}/);
}
function no2(value) {
  return !(parseInt(value) > 60);
}
function no3(value) {
  return !(value[0] == "0");
}
function pass(value) {
  if (!(value.match(/[0-9]/) && value.match(/[a-z]/) && value.match(/[A-Z]/))) {
    return false;
  }
  return true;
}
function images() {
  return !(this.images.length == 0);
}
function fullname(value) {
  if (value.match(/[0-9]/)) {
    return false;
  }
  return true;
}

export default {
  data() {
    return {
      user: "",
      fname: "",
      lname: "",
      classes: "",
      no: "",
      dob: "",
      address1: "",
      address2: "",
      images: [],
      pass: "",
      repass: "",
      files: [],
      btnShow: true,
      imgName: "",
    };
  },

  validations: {
    user: {
      required,
      numeric: numeric,
      user: user,
      maxLength: maxLength(10),
    },
    fname: {
      required,
      maxLength: maxLength(30),
      fullname: fullname,
    },
    lname: {
      required,
      maxLength: maxLength(30),
      fullname: fullname,
    },
    classes: {
      required,
      classes: classes,
    },
    no: {
      required,
      numeric: numeric,
      no2: no2,
      no3: no3,
      maxLength: maxLength(2),
      minLength: minLength(1),
    },
    dob: {
      required,
      maxValue: (value) => value < new Date().toISOString(),
    },
    address1: {
      required,
    },
    images: {
      images: images,
    },
    pass: {
      required,
      minLength: minLength(5),
      maxLength: maxLength(15),
      pass: pass,
    },
    repass: {
      required,
      repass: sameAs("pass"),
    },
  },
  methods: {
    validate() {
      this.$v.$touch();

      if (!this.$v.$invalid) {
        this.register();
      }
    },
    selectImages(event) {
      this.images = event.target.files;

      this.files = event.target.files;
      for (let i = 0; i < this.files.length; i++) {
        console.log(this.files[i]);
      }
    },
    showSelectImage(image) {
      // for preview only
      return URL.createObjectURL(image);
    },
    register() {
      let user = this.user;
      let fname = this.fname;
      let lname = this.lname;
      let classes = this.classes;
      let no = this.no;
      let dob = this.dob;
      let address = this.address1 + this.address2;
      let pass = this.pass;
      let router = this.$router;

      this.btnShow = false;
      //checks if files are selected
      if (this.files.length != 0) {
        //Loops through all the selected files
        for (let i = 0; i < this.files.length; i++) {
          //create a storage reference
          this.imgName = "" + new Date().toISOString();
          var storage = firebase.storage().ref(this.imgName);

          //upload file
          var upload = storage.put(this.files[i]);

          //update progress bar
          upload.on(
            "state_changed",
            function progress() {},

            function error() {
              alert("error uploading file");
            },
            function complete() {
              storage
                .getDownloadURL()
                .then(function (url) {
                  axios
                    .post("http://localhost:5000/register", {
                      user: user,
                      fname: fname,
                      lname: lname,
                      classes: classes,
                      no: no,
                      dob: dob,
                      address: address,
                      pass: pass,
                      image: url,
                    })
                    .then((response) => {
                      const data = response.data;
                      if (data.status) {
                        alert(data.message);
                        router.push({ name: "index" });
                      } else {
                        alert(data.message);
                      }
                    })
                    .catch((error) => {
                      console.log(error);
                    });
                })
                .catch(function (error) {
                  console.log(error);
                });
            }
          );
        }
      } else {
        alert("No file chosen");
      }
    },
  },
};
</script>
<style scoped>
</style>