import {Component, OnInit} from '@angular/core';
import {UserService} from "./services/user.service";
import {AccessService} from "./services/access.service";
import {User} from "./model/security/user";
import {Router} from "@angular/router";
import {environment} from "../environments/environment";

declare let jquery: any;
declare let $: any;
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  ngOnInit(): void {
  }

  loggedInUserName: string;
  errorTitle: string;
  errorMessage: string;
  loggedInUser: User;

  constructor(private userService: UserService,
              public accessService: AccessService,
              private router: Router) {
    let ref = this;
    this.userService.getUser().then(function (response) {
      if (response.success) {
        ref.loggedInUser = response.data;
        ref.loggedInUserName = response.data.username;
        ref.accessService.setPrivileges(response.data.privileges);
        ref.myInit();
      } else {
        window.location.pathname = "/login";
      }
    });
    this.errorTitle = "Alert";
  }

  logout() {
    this.userService.logout().then(function (response) {
      if (response.success) {
        window.location.pathname = environment.contextPath + "login";
      }
    })
  }

  myInit(): void {
    let ref = this;
    let CURRENT_URL = window.location.href.split('#')[0].split('?')[0],
      $BODY = $('body .nav-md'),
      $MENU_TOGGLE = $('#menu_toggle'),
      $SIDEBAR_MENU = $('#sidebar-menu'),
      $SIDEBAR_FOOTER = $('.sidebar-footer'),
      $LEFT_COL = $('.left_col'),
      $RIGHT_COL = $('.right_col'),
      $NAV_MENU = $('.nav_menu'),
      $FOOTER = $('footer');

    var setContentHeight = function () {
      // reset height
      $RIGHT_COL.css('min-height', $(window).height());

      var bodyHeight = $BODY.outerHeight(),
        footerHeight = $BODY.hasClass('footer_fixed') ? -10 : $FOOTER.height(),
        leftColHeight = $LEFT_COL.eq(1).height()
          + 35,//$SIDEBAR_FOOTER.height(),
        contentHeight = bodyHeight < leftColHeight ? leftColHeight : bodyHeight;

      // normalize content
      contentHeight -= $NAV_MENU.height() + footerHeight;
      $RIGHT_COL.css('min-height', 2*contentHeight);
    };

    $SIDEBAR_MENU.find('a').on('click', function (ev) {
      var $li = $(this).parent();

      if ($li.is('.active')) {
        $li.removeClass('active active-sm');
        $('ul:first', $li).slideUp(function () {
          setContentHeight();
        });
      } else {
        // prevent closing menu if we are on child menu
        if (!$li.parent().is('.child_menu')) {
          $SIDEBAR_MENU.find('li').removeClass('active active-sm');
          $SIDEBAR_MENU.find('li ul').slideUp();
        }

        $li.addClass('active');

        $('ul:first', $li).slideDown(function () {
          setContentHeight();
        });
      }
    });

    // toggle small or large menu
    $MENU_TOGGLE.on('click', function () {
      if ($BODY.hasClass('nav-md')) {
        $SIDEBAR_MENU.find('li.active ul').hide();
        $SIDEBAR_MENU.find('li.active').addClass('active-sm').removeClass('active');
      } else {
        $SIDEBAR_MENU.find('li.active-sm ul').show();
        $SIDEBAR_MENU.find('li.active-sm').addClass('active').removeClass('active-sm');
      }

      $BODY.toggleClass('nav-md nav-sm');

      setContentHeight();

      $('.dataTable').each(function () {
        $(this).dataTable().fnDraw();
      });
    });

    $(document).on('show.bs.modal', '.modal', function () {
      var zIndex = 1040 + (10 * $('.modal:visible').length);
      $(this).css('z-index', zIndex);
      setTimeout(function() {
        $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
      }, 0);
    });

    $('body').on('click', '.cap-tabs li', function () {
      let tabId = $(this).attr('id');
      $('.modal-body').children().each(function () {
        if ($(this).attr('id') == 'tab-content-' + tabId) {
          $(this).removeClass('hide');
        } else if($(this).attr('id') && $(this).attr('id') !== 'tab-content-' + tabId) {
          $(this).addClass('hide');
        }
      });

      $('.cap-tabs li').each(function () {
        if ($(this).attr('id') == tabId) {
          $(this).addClass('active');
        } else {
          $(this).removeClass('active');
        }
      });
      return false;
    });

    // check active menu
    $SIDEBAR_MENU.find('a[href="' + CURRENT_URL + '"]').parent('li').addClass('current-page');

    $SIDEBAR_MENU.find('a').filter(function () {
      return this.href == CURRENT_URL;
    }).parent('li').addClass('current-page').parents('ul').slideDown(function () {
      setContentHeight();
    }).parent().addClass('active');

    // recompute content when resizing
    $(window).smartresize(function () {
      setContentHeight();
    });

    setContentHeight();

    // fixed sidebar

    if ($.fn.mCustomScrollbar) {
      $('.menu_fixed').mCustomScrollbar({
        autoHideScrollbar: true,
        theme: 'minimal',
        mouseWheel: {preventDefault: true}
      });
    }
    // /Sidebar

    // Panel toolbox
    $('.collapse-link').on('click', function () {
      var $BOX_PANEL = $(this).closest('.x_panel'),
        $ICON = $(this).find('i'),
        $BOX_CONTENT = $BOX_PANEL.find('.x_content');

      // fix for some div with hardcoded fix class
      if ($BOX_PANEL.attr('style')) {
        $BOX_CONTENT.slideToggle(200, function () {
          $BOX_PANEL.removeAttr('style');
        });
      } else {
        $BOX_CONTENT.slideToggle(200);
        $BOX_PANEL.css('height', 'auto');
      }

      $ICON.toggleClass('fa-chevron-up fa-chevron-down');
    });

    $('.close-link').click(function () {
      var $BOX_PANEL = $(this).closest('.x_panel');

      $BOX_PANEL.remove();
    });
    // /Panel toolbox

    // Tooltip
    $('[data-toggle="tooltip"]').tooltip({
      container: 'body .nav-md'
    });
    // /Tooltip

    $('.nav.side-menu > li > a').each(function(){
      let view = false;

      $(this).parent().find('ul > li > a').each(function () {
        if (!ref.accessService.containsCodeAfterInit($(this).attr('privilege'))) {
          $(this).parent().addClass('hide');
        } else {
          view = true;
        }
      });
      if (!ref.accessService.containsGroupName($(this).parent().attr('groupName')) || !view) {
        $(this).parent().addClass('hide');
      }
    })
  }

  title = 'app';
}
