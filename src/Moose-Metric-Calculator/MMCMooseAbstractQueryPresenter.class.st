"
I'm the super class of invocation and navigation
"
Class {
	#name : #MMCMooseAbstractQueryPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'ingoing',
		'outgoing',
		'queriesOKButton'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseAbstractQueryPresenter class >> defaultSpec [
	^ SpecTableLayout column
		newRow: [ :r | 
			r
				add: #ingoing;
				add: #outgoing ];
		add: #queriesOKButton;
		yourself
]

{ #category : #accessing }
MMCMooseAbstractQueryPresenter >> ingoing [
	^ ingoing
]

{ #category : #initialization }
MMCMooseAbstractQueryPresenter >> initializeWidgets [
	ingoing := self instantiate: RadioButtonPresenter.
	outgoing := self instantiate: RadioButtonPresenter.
	ingoing label: 'Ingoing'.
	outgoing label: 'Outgoing'.
	RadioButtonGroupPresenter new
		addRadioButton: ingoing;
		addRadioButton: outgoing;
		default: ingoing.
	queriesOKButton := self newButton
]

{ #category : #accessing }
MMCMooseAbstractQueryPresenter >> outgoing [
	^ outgoing
]

{ #category : #accessing }
MMCMooseAbstractQueryPresenter >> queriesOKButton [
	^ queriesOKButton
]
