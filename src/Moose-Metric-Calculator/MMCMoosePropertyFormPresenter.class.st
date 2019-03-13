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
		add: #queriesDropDown;
		newRow: [ :r | r add: #queriesOperationsDropDown right: 50 ];
		add: #queryValueInput;
		add: #queriesOKButton;
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
MMCMoosePropertyFormPresenter >> update [
	
]

{ #category : #initialization }
MMCMoosePropertyFormPresenter >> updateQueriesDropListPresenter [
 
	

]
