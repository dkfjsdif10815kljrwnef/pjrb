!(function (a) {
  "use strict";
  function t() {
    (this.body = a("body")),
      (this.window = a(window)),
      (this.menuContainer = a("#left-side-menu-container"));
  }
  (t.prototype._reset = function () {
    this.body.removeAttr("data-leftbar-theme");
  }),
    (t.prototype.activateCondensedSidebar = function () {
      this.body.attr("data-leftbar-compact-mode", "condensed");
    }),
    (t.prototype.deactivateCondensedSidebar = function () {
      this.body.removeAttr("data-leftbar-compact-mode");
    }),
    (t.prototype.activateScrollableSidebar = function () {
      this.body.attr("data-leftbar-compact-mode", "scrollable");
    }),
    (t.prototype.deactivateScrollableSidebar = function () {
      this.body.removeAttr("data-leftbar-compact-mode");
    }),
    (t.prototype.activateDefaultTheme = function () {
      this._reset();
    }),
    (t.prototype.activateLightTheme = function () {
      this._reset(), this.body.attr("data-leftbar-theme", "light");
    }),
    (t.prototype.activateDarkTheme = function () {
      this._reset(), this.body.attr("data-leftbar-theme", "dark");
    }),
    (t.prototype.initMenu = function () {
      var e = this;
      this._reset(),
        a(".side-nav").metisMenu(),
        a(document).on("click", ".button-menu-mobile", function (t) {
          t.preventDefault(), e.body.toggleClass("sidebar-enable");
        }),
        a(".side-nav a").each(function () {
          var t = window.location.href.split(/[?#]/)[0];
          this.href == t &&
            (a(this).addClass("active"),
            a(this).parent().addClass("mm-active"),
            a(this).parent().parent().addClass("mm-show"),
            a(this).parent().parent().prev().addClass("active"),
            a(this).parent().parent().parent().addClass("mm-active"),
            a(this).parent().parent().parent().parent().addClass("mm-show"),
            a(this)
              .parent()
              .parent()
              .parent()
              .parent()
              .parent()
              .addClass("mm-active"),
            a(this)
              .parent()
              .parent()
              .parent()
              .parent()
              .parent()
              .parent()
              .addClass("mm-show"),
            a(this)
              .parent()
              .parent()
              .parent()
              .parent()
              .parent()
              .parent()
              .parent()
              .addClass("mm-active"));
        });
    }),
    (t.prototype.init = function () {
      this.initMenu();
    }),
    (a.LeftSidebar = new t()),
    (a.LeftSidebar.Constructor = t);
})(window.jQuery),
  (function (a) {
    "use strict";
    function t() {
      (this.$body = a("body")), (this.$window = a(window));
    }
    (t.prototype.initMenu = function () {
      a(".topnav-menu").length &&
        (a(".topnav-menu li a").each(function () {
          var t = window.location.href.split(/[?#]/)[0];
          this.href == t &&
            (a(this).addClass("active"),
            a(this).parent().parent().addClass("active"),
            a(this).parent().parent().parent().parent().addClass("active"),
            a(this)
              .parent()
              .parent()
              .parent()
              .parent()
              .parent()
              .parent()
              .addClass("active"));
        }),
        a(".navbar-toggle").on("click", function () {
          a(this).toggleClass("open"), a("#navigation").slideToggle(400);
        }));
    }),
      (t.prototype.initSearch = function () {
        var e = a(".navbar-custom .dropdown:not(.app-search)");
        a(document).on("click", function (t) {
          return (
            0 === a(t.target).closest("#search-dropdown").length &&
              a("#search-dropdown").removeClass("d-block"),
            !0
          );
        }),
          a("#top-search").on("click", function (t) {
            return (
              t.preventDefault(),
              e.children(".dropdown-menu.show").removeClass("show"),
              a("#search-dropdown").addClass("d-block"),
              !1
            );
          }),
          e.on("show.bs.dropdown", function () {
            a("#search-dropdown").removeClass("d-block");
          });
      }),
      (t.prototype.init = function () {
        this.initMenu(), this.initSearch();
      }),
      (a.Topbar = new t()),
      (a.Topbar.Constructor = t);
  })(window.jQuery),
  (function (a) {
    "use strict";
    function t() {
      (this.body = a("body")), (this.window = a(window));
    }
    (t.prototype._selectOptionsFromConfig = function () {
      var t = a.App.getLayoutConfig();
      if (t) {
        switch (t.sideBarTheme) {
          case "default":
            a("#default-check").prop("checked", !0);
            break;
          case "light":
            a("#light-check").prop("checked", !0);
            break;
          case "dark":
            a("#dark-check").prop("checked", !0);
        }
        t.isBoxed
          ? a("#boxed-check").prop("checked", !0)
          : a("#fluid-check").prop("checked", !0),
          t.isCondensed && a("#condensed-check").prop("checked", !0),
          t.isScrollable && a("#scrollable-check").prop("checked", !0),
          t.isScrollable ||
            t.isCondensed ||
            a("#fixed-check").prop("checked", !0),
          t.isDarkModeEnabled ||
            (a("#light-mode-check").prop("checked", !0),
            "vertical" === t.layout &&
              a("input[type=radio][name=theme]").prop("disabled", !1)),
          t.isDarkModeEnabled &&
            (a("#dark-mode-check").prop("checked", !0),
            "vertical" === t.layout &&
              a("input[type=radio][name=theme]").prop("disabled", !1));
      }
    }),
      (t.prototype.toggleRightSideBar = function () {
        this.body.toggleClass("right-bar-enabled"),
          this._selectOptionsFromConfig();
      }),
      (t.prototype.init = function () {
        var e = this;
        a(document).on("click", ".right-bar-toggle", function () {
          e.toggleRightSideBar();
        }),
          a(document).on("click", "body", function (t) {
            0 < a(t.target).closest(".right-bar-toggle, .right-bar").length ||
              0 < a(t.target).closest(".left-side-menu, .side-nav").length ||
              a(t.target).hasClass("button-menu-mobile") ||
              0 < a(t.target).closest(".button-menu-mobile").length ||
              (a("body").removeClass("right-bar-enabled"),
              a("body").removeClass("sidebar-enable"));
          }),
          a("input[type=radio][name=width]").change(function () {
            switch (a(this).val()) {
              case "fluid":
                a.App.activateFluid();
                break;
              case "boxed":
                a.App.activateBoxed();
            }
          }),
          a("input[type=radio][name=theme]").change(function () {
            switch (a(this).val()) {
              case "default":
                a.App.activateDefaultSidebarTheme();
                break;
              case "light":
                a.App.activateLightSidebarTheme();
                break;
              case "dark":
                a.App.activateDarkSidebarTheme();
            }
          }),
          a("input[type=radio][name=compact]").change(function () {
            switch (a(this).val()) {
              case "fixed":
                a.App.deactivateCondensedSidebar(),
                  a.App.deactivateScrollableSidebar();
                break;
              case "scrollable":
                a.App.activateScrollableSidebar();
                break;
              case "condensed":
                a.App.activateCondensedSidebar();
            }
          }),
          a("input[type=radio][name=color-scheme-mode]").change(function () {
            switch (a(this).val()) {
              case "light":
                a.App.deactivateDarkMode(),
                  a.App.activateDefaultSidebarTheme(),
                  a("#default-check").prop("checked", !0),
                  a("input[type=radio][name=theme]").prop("disabled", !1);
                break;
              case "dark":
                a.App.activateDarkMode(), a("#dark-check").prop("checked", !0);
            }
          }),
          a("#resetBtn").on("click", function (t) {
            t.preventDefault(),
              a.App.resetLayout(function () {
                e._selectOptionsFromConfig();
              });
          });
      }),
      (a.RightBar = new t()),
      (a.RightBar.Constructor = t);
  })(window.jQuery),
  (function (a) {
    "use strict";
    function t() {
      (this.body = a("body")),
        (this.window = a(window)),
        (this._config = {}),
        (this.defaultSelectedStyle = null);
    }
    var e = "default",
      i = "light",
      o = "dark",
      n = {
        sideBarTheme: e,
        isBoxed: !1,
        isCondensed: !1,
        isScrollable: !1,
        isDarkModeEnabled: !1,
      };
    (t.prototype._saveConfig = function (t) {
      a.extend(this._config, t);
    }),
      (t.prototype._getStoredConfig = function () {
        var t = this.body.data("layoutConfig"),
          e = n;
        return (
          t &&
            ((e.sideBarTheme = t.leftSideBarTheme),
            (e.isBoxed = t.layoutBoxed),
            (e.isCondensed = t.leftSidebarCondensed),
            (e.isScrollable = t.leftSidebarScrollable),
            (e.isDarkModeEnabled = t.darkMode)),
          e
        );
      }),
      (t.prototype._applyConfig = function () {
        var t = this;
        switch (
          ((this._config = this._getStoredConfig()),
          a.LeftSidebar.init(),
          t._config.sideBarTheme)
        ) {
          case o:
            t.activateDarkSidebarTheme();
            break;
          case i:
            t.activateLightSidebarTheme();
        }
        t._config.isDarkModeEnabled
          ? t.activateDarkMode()
          : t.deactivateDarkMode(),
          t._config.isBoxed && t.activateBoxed(),
          t._config.isCondensed && t.activateCondensedSidebar(),
          t._config.isScrollable && t.activateScrollableSidebar();
      }),
      (t.prototype._adjustLayout = function () {
        if (750 <= this.window.width() && this.window.width() <= 1028)
          this.activateCondensedSidebar(!0);
        else {
          var t = this._getStoredConfig();
          t.isCondensed || t.isScrollable || this.deactivateCondensedSidebar();
        }
      }),
      (t.prototype.activateFluid = function () {
        this._saveConfig({ isBoxed: !1 }),
          this.body.attr("data-layout-mode", "fluid");
      }),
      (t.prototype.activateBoxed = function () {
        this._saveConfig({ isBoxed: !0 }),
          this.body.attr("data-layout-mode", "boxed");
      }),
      (t.prototype.activateCondensedSidebar = function (t) {
        t || this._saveConfig({ isCondensed: !0, isScrollable: !1 }),
          a.LeftSidebar.activateCondensedSidebar();
      }),
      (t.prototype.deactivateCondensedSidebar = function () {
        this._saveConfig({ isCondensed: !1 }),
          a.LeftSidebar.deactivateCondensedSidebar();
      }),
      (t.prototype.activateScrollableSidebar = function () {
        this._saveConfig({ isScrollable: !0, isCondensed: !1 }),
          a.LeftSidebar.activateScrollableSidebar();
      }),
      (t.prototype.deactivateScrollableSidebar = function () {
        this._saveConfig({ isScrollable: !1 }),
          a.LeftSidebar.deactivateScrollableSidebar();
      }),
      (t.prototype.activateDefaultSidebarTheme = function () {
        a.LeftSidebar.activateDefaultTheme(),
          this._saveConfig({ sideBarTheme: e });
      }),
      (t.prototype.activateLightSidebarTheme = function () {
        a.LeftSidebar.activateLightTheme(),
          this._saveConfig({ sideBarTheme: i });
      }),
      (t.prototype.activateDarkSidebarTheme = function () {
        a.LeftSidebar.activateDarkTheme(),
          this._saveConfig({ sideBarTheme: o });
      }),
      (t.prototype.activateDarkMode = function () {
        a("#light-style").attr("disabled", !0),
          a("#dark-style").attr("disabled", !1),
          a.LeftSidebar.activateDarkTheme(),
          this._saveConfig({ isDarkModeEnabled: !0, sideBarTheme: o });
      }),
      (t.prototype.deactivateDarkMode = function () {
        a("#light-style").attr("disabled", !1),
          a("#dark-style").attr("disabled", !0),
          this._saveConfig({ isDarkModeEnabled: !1 });
      }),
      (t.prototype.clearSavedConfig = function () {
        this._config = n;
      }),
      (t.prototype.getConfig = function () {
        return this._config;
      }),
      (t.prototype.reset = function (t) {
        this.clearSavedConfig();
        var e = this;
        a("#main-style-container").length &&
          (e.defaultSelectedStyle = a("#main-style-container").attr("href")),
          e.deactivateCondensedSidebar(),
          e.deactivateDarkMode(),
          e.activateDefaultSidebarTheme(),
          e.activateFluid(),
          t();
      }),
      (t.prototype.init = function () {
        var e = this;
        a("#main-style-container").length &&
          (e.defaultSelectedStyle = a("#main-style-container").attr("href")),
          this._applyConfig(),
          this._adjustLayout(),
          this.window.on("resize", function (t) {
            t.preventDefault(), e._adjustLayout();
          }),
          a.Topbar.init();
      }),
      (a.LayoutThemeApp = new t()),
      (a.LayoutThemeApp.Constructor = t);
  })(window.jQuery),
  (function (o) {
    "use strict";
    function t() {
      (this.$body = o("body")),
        (this.$portletIdentifier = ".card"),
        (this.$portletCloser = '.card a[data-toggle="remove"]'),
        (this.$portletRefresher = '.card a[data-toggle="reload"]');
    }
    (t.prototype.init = function () {
      var i = this;
      o(document).on("click", this.$portletCloser, function (t) {
        t.preventDefault();
        var e = o(this).closest(i.$portletIdentifier),
          a = e.parent();
        e.remove(), 0 == a.children().length && a.remove();
      }),
        o(document).on("click", this.$portletRefresher, function (t) {
          t.preventDefault();
          var e = o(this).closest(i.$portletIdentifier);
          e.append(
            '<div class="card-disabled"><div class="card-portlets-loader"></div></div>'
          );
          var a = e.find(".card-disabled");
          setTimeout(function () {
            a.fadeOut("fast", function () {
              a.remove();
            });
          }, 500 + 5 * Math.random() * 300);
        });
    }),
      (o.Portlet = new t()),
      (o.Portlet.Constructor = t);
  })(window.jQuery),
  (function (n) {
    "use strict";
    function t() {
      (this.$body = n("body")), (this.$window = n(window));
    }
    (t.prototype.initSelect2 = function () {
      n('[data-toggle="select2"]').select2();
    }),
      (t.prototype.initMask = function () {
        n('[data-toggle="input-mask"]').each(function (t, e) {
          var a = n(e).data("maskFormat"),
            i = n(e).data("reverse");
          null != i ? n(e).mask(a, { reverse: i }) : n(e).mask(a);
        });
      }),
      (t.prototype.initDateRange = function () {
        var i = { cancelClass: "btn-light", applyButtonClasses: "btn-success" };
        n('[data-toggle="date-picker"]').each(function (t, e) {
          var a = n.extend({}, i, n(e).data());
          n(e).daterangepicker(a);
        });
        var o = {
          startDate: moment().subtract(29, "days"),
          endDate: moment(),
          ranges: {
            Today: [moment(), moment()],
            Yesterday: [
              moment().subtract(1, "days"),
              moment().subtract(1, "days"),
            ],
            "Last 7 Days": [moment().subtract(6, "days"), moment()],
            "Last 30 Days": [moment().subtract(29, "days"), moment()],
            "This Month": [moment().startOf("month"), moment().endOf("month")],
            "Last Month": [
              moment().subtract(1, "month").startOf("month"),
              moment().subtract(1, "month").endOf("month"),
            ],
          },
        };
        n('[data-toggle="date-picker-range"]').each(function (t, e) {
          var a = n.extend({}, o, n(e).data()),
            i = a.targetDisplay;
          n(e).daterangepicker(a, function (t, e) {
            i &&
              n(i).html(
                t.format("MMMM D, YYYY") + " - " + e.format("MMMM D, YYYY")
              );
          });
        });
      }),
      (t.prototype.initTimePicker = function () {
        var i = {
          showSeconds: !0,
          icons: { up: "mdi mdi-chevron-up", down: "mdi mdi-chevron-down" },
        };
        n('[data-toggle="timepicker"]').each(function (t, e) {
          var a = n.extend({}, i, n(e).data());
          n(e).timepicker(a);
        });
      }),
      (t.prototype.initTouchspin = function () {
        var i = {};
        n('[data-toggle="touchspin"]').each(function (t, e) {
          var a = n.extend({}, i, n(e).data());
          n(e).TouchSpin(a);
        });
      }),
      (t.prototype.initMaxlength = function () {
        var i = {
          warningClass: "badge badge-success",
          limitReachedClass: "badge badge-danger",
          separator: "??? ??? ",
          preText: "?????? ????????? ",
          postText: " ?????? ??????????????????.",
          placement: "bottom",
        };
        n('[data-toggle="maxlength"]').each(function (t, e) {
          var a = n.extend({}, i, n(e).data());
          n(e).maxlength(a);
        });
      }),
      (t.prototype.init = function () {
        this.initSelect2(),
          this.initMask(),
          this.initDateRange(),
          this.initTimePicker(),
          this.initTouchspin(),
          this.initMaxlength();
      }),
      (n.AdvanceFormApp = new t()),
      (n.AdvanceFormApp.Constructor = t);
  })(window.jQuery),
  (function (c) {
    "use strict";
    function t() {}
    (t.prototype.send = function (t, e, a, i, o, n, r, d) {
      var s = {
        heading: t,
        text: e,
        position: a,
        loaderBg: i,
        icon: o,
        hideAfter: (n = n || 3e3),
        stack: (r = r || 1),
      };
      (s.showHideTransition = d || "fade"), c.toast().reset("all"), c.toast(s);
    }),
      (c.NotificationApp = new t()),
      (c.NotificationApp.Constructor = t);
  })(window.jQuery),
  (function (e) {
    "use strict";
    function t() {}
    (t.prototype.initTooltipPlugin = function () {
      e.fn.tooltip && e('[data-toggle="tooltip"]').tooltip();
    }),
      (t.prototype.initPopoverPlugin = function () {
        e.fn.popover && e('[data-toggle="popover"]').popover();
      }),
      (t.prototype.initToastPlugin = function () {
        e.fn.toast && e('[data-toggle="toast"]').toast();
      }),
      (t.prototype.initFormValidation = function () {
        e(".needs-validation").on("submit", function (t) {
          return (
            e(this).addClass("was-validated"),
            !1 !== e(this)[0].checkValidity() ||
              (t.preventDefault(), t.stopPropagation(), !1)
          );
        });
      }),
      (t.prototype.initShowHidePassword = function () {
        e("[data-password]").on("click", function () {
          "false" == e(this).attr("data-password")
            ? (e(this).siblings("input").attr("type", "text"),
              e(this).attr("data-password", "true"),
              e(this).addClass("show-password"))
            : (e(this).siblings("input").attr("type", "password"),
              e(this).attr("data-password", "false"),
              e(this).removeClass("show-password"));
        });
      }),
      (t.prototype.initSyntaxHighlight = function () {
        e(document).ready(function (t) {
          document.querySelectorAll("pre span.escape").forEach(function (t, e) {
            if (t.classList.contains("escape")) var a = t.innerText;
            else a = t.innerText;
            for (
              var i = 1 / 0,
                o = (a = a.replace(/^\n/, "").trimRight()).split("\n"),
                n = 0;
              n < o.length;
              n++
            )
              o[n].trim() && (i = Math.min(o[n].search(/\S/), i));
            var r = [];
            for (n = 0; n < o.length; n++)
              r.push(o[n].replace(new RegExp("^ {" + i + "}", "g"), ""));
            t.innerText = r.join("\n");
          }),
            document.querySelectorAll("pre span.escape").forEach(function (t) {
              hljs.highlightBlock(t);
            });
        });
      }),
      (t.prototype.init = function () {
        this.initTooltipPlugin(),
          this.initPopoverPlugin(),
          this.initToastPlugin(),
          this.initFormValidation(),
          this.initShowHidePassword(),
          this.initSyntaxHighlight();
      }),
      (e.Components = new t()),
      (e.Components.Constructor = t);
  })(window.jQuery),
  (function (e) {
    "use strict";
    function t() {
      (this.$body = e("body")), (this.$window = e(window));
    }
    (t.prototype.activateDefaultSidebarTheme = function () {
      e.LayoutThemeApp.activateDefaultSidebarTheme();
    }),
      (t.prototype.activateLightSidebarTheme = function () {
        e.LayoutThemeApp.activateLightSidebarTheme();
      }),
      (t.prototype.activateDarkSidebarTheme = function () {
        e.LayoutThemeApp.activateDarkSidebarTheme();
      }),
      (t.prototype.activateCondensedSidebar = function () {
        e.LayoutThemeApp.activateCondensedSidebar();
      }),
      (t.prototype.deactivateCondensedSidebar = function () {
        e.LayoutThemeApp.deactivateCondensedSidebar();
      }),
      (t.prototype.activateScrollableSidebar = function () {
        e.LayoutThemeApp.activateScrollableSidebar();
      }),
      (t.prototype.deactivateScrollableSidebar = function () {
        e.LayoutThemeApp.deactivateScrollableSidebar();
      }),
      (t.prototype.activateBoxed = function () {
        e.LayoutThemeApp.activateBoxed();
      }),
      (t.prototype.activateFluid = function () {
        e.LayoutThemeApp.activateFluid();
      }),
      (t.prototype.activateDarkMode = function () {
        e.LayoutThemeApp.activateDarkMode();
      }),
      (t.prototype.deactivateDarkMode = function () {
        e.LayoutThemeApp.deactivateDarkMode();
      }),
      (t.prototype.clearSavedConfig = function () {
        e.LayoutThemeApp.clearSavedConfig();
      }),
      (t.prototype.getLayoutConfig = function () {
        return e.LayoutThemeApp.getConfig();
      }),
      (t.prototype.resetLayout = function (t) {
        e.LayoutThemeApp.reset(t);
      }),
      (t.prototype.init = function () {
        e.LayoutThemeApp.init(),
          setTimeout(function () {
            document.body.classList.remove("loading");
          }, 400),
          e.RightBar.init();
        var t = this.$body.data("layoutConfig");
        window.sessionStorage &&
          t &&
          t.hasOwnProperty("showRightSidebarOnStart") &&
          t.showRightSidebarOnStart &&
          (sessionStorage.getItem("_HYPER_VISITED_") ||
            (e.RightBar.toggleRightSideBar(),
            sessionStorage.setItem("_HYPER_VISITED_", !0)));
        e.Portlet.init(),
          e.AdvanceFormApp.init(),
          e.Components.init(),
          e(window).on("load", function () {
            e("#status").fadeOut(), e("#preloader").delay(350).fadeOut("slow");
          });
      }),
      (e.App = new t()),
      (e.App.Constructor = t);
  })(window.jQuery),
  (function () {
    "use strict";
    window.jQuery.App.init();
  })();

$(document).ready(function () {
  //????????? ???????????? ??????
  $(".checkall").click(function () {
    //??????????????????
    if ($(".checkall").prop("checked")) {
      //input????????? name??? chk??? ???????????? ????????? checked????????? true??? ??????
      $("input[name=chk]").prop("checked", true);
      //????????? ???????????????
    } else {
      //input????????? name??? chk??? ???????????? ????????? checked????????? false??? ??????
      $("input[name=chk]").prop("checked", false);
    }
  });
});

/* ???????????? */
$(document).on("keyup", ".phoneNumber", function () {
  $(this).val(
    $(this)
      .val()
      .replace(/[^0-9]/g, "")
      .replace(
        /(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,
        "$1-$2-$3"
      )
      .replace("--", "-")
  );
});

/* ????????? ?????? */
$(document).on("keyup", ".inNumber", function () {
  //this.value=this.value.replace(/[^0-9]/g,'');
  $(this).val(
    $(this)
      .val()
      .replace(/[^0-9]/g, "")
  );
});

/* ??????????????? */
$(document).ready(function () {
  $(function () {
    $(".company_no").keydown(function (event) {
      var key = event.charCode || event.keyCode || 0;
      $text = $(this);
      if (key !== 8 && key !== 9) {
        if ($text.val().length === 3) {
          $text.val($text.val() + "-");
        }
        if ($text.val().length === 6) {
          $text.val($text.val() + "-");
        }
      }

      return (
        key == 8 ||
        key == 9 ||
        key == 46 ||
        (key >= 48 && key <= 57) ||
        (key >= 96 && key <= 105)
      );
      // Key 8??? ???????????????, Key 9??? ???, Key 46??? Delete ?????? 0 ~ 9??????, Key 96 ~ 105?????? ????????????
      // ???????????? JQuery 0 ~~~ 9 ?????? ???????????????, ???, Delete ??? ????????????????????? ????????????
    });
  });
});
