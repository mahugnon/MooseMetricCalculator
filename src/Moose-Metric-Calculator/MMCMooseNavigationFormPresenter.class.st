Class {
	#name : #MMCMooseNavigationFormPresenter,
	#superclass : #MMCMooseAbstractQueryPresenter,
	#instVars : [
		'nameInput',
		'modelPresenter',
		'modelLabel'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseNavigationFormPresenter class >> defaultSpec [
	| spec |
	spec := super defaultSpec.
	^ spec
		newRow: [ :r | r add: #nameInput ];
		newRow: [ :r | 
			r
				add: #modelLabel;
				add: #modelPresenter ];
		yourself
]

{ #category : #specs }
MMCMooseNavigationFormPresenter class >> runMe [
<example>
self new openWithSpec 
]

{ #category : #api }
MMCMooseNavigationFormPresenter >> extent [ 
^250@250
]

{ #category : #initialization }
MMCMooseNavigationFormPresenter >> initializeWidgets [
	super initializeWidgets.
	nameInput := self newTextInput.
	nameInput ghostText: 'Navigation name'.
	modelPresenter := self newDropList.
	modelLabel := self newLabel
		label: 'For :';
		emphasis: #bold;
		yourself
]

{ #category : #initialization }
MMCMooseNavigationFormPresenter >> modelLabel [
	^ modelLabel
]

{ #category : #initialization }
MMCMooseNavigationFormPresenter >> modelPresenter [
	^ modelPresenter
]

{ #category : #accessing }
MMCMooseNavigationFormPresenter >> name [
	^ 'Navigation'
]

{ #category : #accessing }
MMCMooseNavigationFormPresenter >> nameInput [
	^ nameInput
]
