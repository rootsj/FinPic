<template>
  <div id="tagSearchBox"
    v-on:keyup.down="selectValue('down')"
    v-on:keyup.up="selectValue('up')">
    <div class="search">
      <input class="s" placeholder="검색" v-on:input="searchQuery=event.target.value" />
      <ul class="r" tabindex="0" v-bind:class="{ show: isActive }" v-on:mouseover="removeValue">
        <li
          tabindex="-1"
          v-for="(el, index) in filterList"
          v-on:click="changeValue(el.tag)"
          v-on:keyup.enter="selectValue('enter', el.name)"
          :key="(el, index)"
        >
          <span>{{ el.tag }}</span>
        </li>
      </ul>
    </div>
  </div>
</template>


<script>
const tags = [
  { name: '김건모 잘못된 만남' },
  { name: '홍길동' },
  { name: '손오공' },
  { name: '공민지' },
  { name: '장동건' },
  { name: '장희빈' },
  { name: '고추장' },
  { name: '된장' },
  { name: '장장' },
  { name: '희야' },
];

export default {
  name: 'tagSearchBox',
  data: function(){
    return {
      isActive: false,
      searchQuery: '',
      tags: tags,
    }
  },
  methods: {
    changeValue(str) {
      this.isActive = false;
      document.querySelector('.s').value = str;
    },
    selectValue(keycode, str) {
      if (this.isActive === true) {
        const hasClass = document.querySelector('.r').classList.contains('key');
        if (keycode === 'down') {
          if (!hasClass) {
            const thisEl = document.querySelectorAll('.r li')[0];
            document.querySelector('.r').classList.add('key');
            thisEl.classList.add('sel');
            thisEl.focus();
          } else {
            const lastEl = document.querySelector('.r li:last-child');
            const thisEl = document.querySelector('.r li.sel');
            const nextEl = thisEl.nextElementSibling;
            if (!lastEl.classList.contains('sel')) {
              thisEl.classList.remove('sel');
              nextEl.classList.add('sel');
              nextEl.focus();
            }
          }
        }
        if (keycode === 'up' && hasClass) {
          const firstEl = document.querySelectorAll('.r li')[0];
          const thisEl = document.querySelector('.r li.sel');
          const prevEl = thisEl.previousElementSibling;
          if (!firstEl.classList.contains('sel')) {
            thisEl.classList.remove('sel');
            prevEl.classList.add('sel');
            prevEl.focus();
          } else {
            document.querySelector('.s').focus();
          }
        }
        if (keycode === 'enter' && hasClass) {
          this.changeValue(str);
        }
      }
    },
    removeValue() {
      if (document.querySelector('.r').classList.contains('key')) {
        document.querySelector('.r').classList.remove('key');
        document.querySelector('.r li.sel').classList.remove('sel');
      }
    },
  },
  computed: {
    filterList() {
      const str = this.searchQuery;
      const reg = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9|\s]/.test(str);
      if (reg === false && str !== '' && str !== ' ') {
        this.isActive = true;
        return this.tags.filter((el) => {
          return el.tags.match(str);
        });
      } else {
        return this.isActive = false;
      }
    },
  }
};
</script>