import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'lms-alert-modal',
  templateUrl: 'lms-alert.modal.html',
})

export class LMSAlertModal implements OnInit{

  ngOnInit(): void {
  }

  @Input() modalMessage: string;
  @Input() modalTitle: string;
}
