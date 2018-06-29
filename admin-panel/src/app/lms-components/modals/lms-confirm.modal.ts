import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'lms-confirm-modal',
  templateUrl: 'lms-confirm.modal.html',
})
export class LMSConfirmModalComponent implements OnInit{

  ngOnInit(): void {
  }

  @Input() modalMessage: string;
  @Input() modalTitle: string;

  @Output() onOkClick = new EventEmitter();

  private onOkClicked(event) {
      this.onOkClick.emit({ event });
  }
}
