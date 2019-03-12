Class {
	#name : #MMCMooseNavigationFormPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'ingoing',
		'outgoing',
		'queriesOKButton'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseNavigationFormPresenter class >> defaultSpec [
	^ SpecTableLayout column
		newRow: [ :r | 
			r
				add: #ingoing;
				add: #outgoing ];
		add: #queriesOKButton;
		yourself
]

{ #category : #specs }
MMCMooseNavigationFormPresenter class >> runMe [
<example>
self new openWithSpec 
]

{ #category : #accessing }
MMCMooseNavigationFormPresenter >> ingoing [
	^ ingoing
]

{ #category : #initialization }
MMCMooseNavigationFormPresenter >> initializeWidgets [
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
MMCMooseNavigationFormPresenter >> name [
	^ 'Navigation'
]

{ #category : #accessing }
MMCMooseNavigationFormPresenter >> outgoing [
	^ outgoing
]

{ #category : #accessing }
MMCMooseNavigationFormPresenter >> queriesOKButton [
	^ queriesOKButton
]
