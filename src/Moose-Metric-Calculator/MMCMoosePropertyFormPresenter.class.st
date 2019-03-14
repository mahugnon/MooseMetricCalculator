"
I'm the property presenter widget
"
Class {
	#name : #MMCMoosePropertyFormPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'queriesDropDown',
		'queriesOperationsDropDown',
		'queryValueInput',
		'queriesOKButton'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMoosePropertyFormPresenter class >> defaultSpec [
	^ SpecColumnLayout composed
		newRow: [ :r | r add: #queriesDropDown ] height: 50;
		newRow:
				[ :r | r add: #queriesOperationsDropDown origin: 0 @ 1 corner: 0.3 @ 1.2 ]
			height: 30;
		add: #queryValueInput height: 40;
		add: #queriesOKButton height: 50;
		yourself
]

{ #category : #examples }
MMCMoosePropertyFormPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #initialization }
MMCMoosePropertyFormPresenter >> initQueriesDropListPresenter [
	queriesDropDown := self instantiate: MMCDropListPresenter.
	queriesDropDown label: self name
]

{ #category : #initialization }
MMCMoosePropertyFormPresenter >> initQueriesOperationsDropDown [
	queriesOperationsDropDown := self newDropList.
	queriesOperationsDropDown
		items: self operations;
		displayBlock: [ :item | item asString ]
]

{ #category : #initialization }
MMCMoosePropertyFormPresenter >> initQueryValueInput [
	queryValueInput := self newTextInput.
	queryValueInput ghostText: self name , ' ' , 'value'
]

{ #category : #initialization }
MMCMoosePropertyFormPresenter >> initializePresenter [
	queriesOKButton action: [ self performSearch ]
]

{ #category : #initialization }
MMCMoosePropertyFormPresenter >> initializeWidgets [
	self initQueriesDropListPresenter.
	self initQueriesOperationsDropDown.
	self initQueryValueInput.
	queriesOKButton := self newButton.
	queriesOKButton
		label: 'Search';
		icon: (self iconNamed: #glamorousSearch)
]

{ #category : #accessing }
MMCMoosePropertyFormPresenter >> name [
	^ 'Property'
]

{ #category : #initialization }
MMCMoosePropertyFormPresenter >> performSearch [
	| param |
	param := Dictionary new.
	param
		at: 'property' put: queriesDropDown selected;
		at: 'operator' put: queriesOperationsDropDown selectedItem;
		at: 'value' put: queryValueInput text asInteger.
	context performSearchWithParameters: param
]

{ #category : #accessing }
MMCMoosePropertyFormPresenter >> queriesDropDown [
	^ queriesDropDown
]

{ #category : #accessing }
MMCMoosePropertyFormPresenter >> queriesOKButton [
	^ queriesOKButton
]

{ #category : #accessing }
MMCMoosePropertyFormPresenter >> queriesOperationsDropDown [
	^ queriesOperationsDropDown
]

{ #category : #accessing }
MMCMoosePropertyFormPresenter >> queryValueInput [
	^ queryValueInput
]

{ #category : #subscription }
MMCMoosePropertyFormPresenter >> subscribeTo: aContext [
	super subscribeTo: aContext.
	queriesDropDown subscribeTo: aContext
]

{ #category : #subscription }
MMCMoosePropertyFormPresenter >> update [
queriesDropDown update
]
